package com.example.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.model.Role;
import com.example.model.UserAccount;
import com.example.service.UserAccountService;

@Component
public class UserAccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	public UserAccountService userAccSer;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
         if(authentication.getCredentials() == null) {
        	 if(userDetails.getPassword() == null){
        		 throw new BadCredentialsException("user details cannot be empty");
        	 }
         }
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserAccount userAccount = userAccSer.findByUserName(username);
		if (userAccount == null) {
			return null;
		}
		//Here Need to check the password from property file to the password from the DB.
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (Role r : userAccount.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(r.getCode()));
		}
		User userDls = new User(userAccount.getUserName(), userAccount.getPassword(), userAccount.isEnabled(),
				userAccount.isAccountNonExpired(), userAccount.isCredentialsNonExpired(), userAccount.isAccountNonLocked(),
				grantedAuthorities);
		return userDls;
	}

}
