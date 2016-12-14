package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.security.UserAccountAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserAccountAuthenticationProvider authenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
		/*
		 * auth.inMemoryAuthentication().withUser("nndini").password("password")
		 * .roles("USER");
		 */
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			  http.csrf().disable();
			  http.headers().frameOptions().disable();
			  http
	            .antMatcher("/**")
	              .authorizeRequests()//.anyRequest().permitAll()
	                .anyRequest().hasRole("USER")
	            .and()
	            .httpBasic()
	            .and()
	            .sessionManagement()
	              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);	;
			  
			  }
	}
}
