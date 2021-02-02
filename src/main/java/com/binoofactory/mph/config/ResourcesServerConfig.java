package com.binoofactory.mph.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.binoofactory.mph.cmmn.data.ServerCode;

@Configuration
public class ResourcesServerConfig extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(ServerCode.DEV_SERVER.getServerCaption());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
	        .authorizeRequests()
            .antMatchers("/api/t1/auth/*").permitAll()
            .antMatchers("/api/t1/board/*").permitAll()
            .antMatchers("/api/t1/info/missing-people").hasRole("USER");
	}
}