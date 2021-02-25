package com.binoofactory.mph.web.auth.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.binoofactory.mph.web.auth.repos.OAuthRepos;
import com.binoofactory.mph.web.auth.vo.OAuthVO;

@Service("userDetailsService")
public class OAuthService implements UserDetailsService {
	@Autowired
	private OAuthRepos oAuthRepos;
//	private CustRepos oAuthRepos;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		OAuthVO authInfo = oAuthRepos.findByUserName(username);
//		CustVO authInfo = oAuthRepos.findById(username);
		if(authInfo == null)
			throw new UsernameNotFoundException("Not Found user!");
		return authInfo;
	}
}
