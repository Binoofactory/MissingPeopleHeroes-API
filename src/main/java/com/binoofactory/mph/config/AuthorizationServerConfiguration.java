package com.binoofactory.mph.config;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.binoofactory.mph.web.auth.svc.OAuthService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter 
{
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(AuthorizationServerConfiguration.class);

	@Autowired 
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired 
	private AuthenticationManager authenticationManager;

	@Autowired 
	private OAuthService oAuthService;
	
    @Autowired
	@Qualifier("encoder")
    private PasswordEncoder encoder;
    
	@Bean
    public JdbcTokenStore tokenStore() 
	{
        return new JdbcTokenStore(dataSource);
    }
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception 
    {
    	clients.jdbc(dataSource);
    }
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() 
    {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }
    
    @Bean
    public JdbcApprovalStore approvalStore() 
    {
        return new JdbcApprovalStore(dataSource);
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception 
    {
    	security
	    	.tokenKeyAccess("permitAll()")
	    	.checkTokenAccess("isAuthenticated()")
	    	.allowFormAuthenticationForClients();
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) 
    {
        endpoints
	        .authenticationManager(authenticationManager)
	        .userDetailsService(oAuthService)
	        .authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource))
	        .approvalStore(approvalStore())
	        .tokenStore(tokenStore());
    }
    
    @Bean
    public ClientRegistrationService clientRegistrationService() 
    {
        return new JdbcClientDetailsService(dataSource);
    }
}