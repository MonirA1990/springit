package com.vega.springit.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
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
			.antMatchers("/link/submit").hasRole("USER")
            .antMatchers("/link/**").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .and()
	        .formLogin()
	        .and()
	        .csrf().disable()
	        .headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl);
	}
}
