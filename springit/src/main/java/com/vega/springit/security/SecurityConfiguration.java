package com.vega.springit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	public SecurityConfiguration(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.authorizeRequests()
			.requestMatchers(EndpointRequest.to("info")).permitAll()
			.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
			.antMatchers("/actuator/").hasRole("ADMIN")
			.antMatchers("/link/submit").hasAnyRole("USER", "ADMIN")
            .antMatchers("/link/**").permitAll()
            .antMatchers("/vote/**").hasRole("USER")
            .antMatchers("/").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .and()
	        .formLogin()
	        .loginPage("/login")
	        .permitAll()
	        .usernameParameter("email")
	        .and()
	        .logout()
	        .and()
	        .rememberMe();
//	        .and()
//	        .csrf().disable()
//	        .headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl);
	}
}
