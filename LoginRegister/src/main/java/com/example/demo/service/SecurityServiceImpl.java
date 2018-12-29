package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//We create SecurityService to provide current loggedin user and auto login user after resgistering an account.

@Service
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(userDetails instanceof UserDetails)
			 return ((UserDetails) userDetails).getUsername();
		
		return null;
			
	}

	@Override
	@Transactional(readOnly=true)
	public void autoLogin(String userName, String password) { 
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				 new UsernamePasswordAuthenticationToken(userName, password, userDetails.getAuthorities());
		System.out.println("authorities : "+userDetails.getAuthorities());
		
		Authentication auth =authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		if(usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			logger.debug(String.format("AUTOLOGIN OF %s SUCCESSFUL", userName));
		}
		
	}
}
