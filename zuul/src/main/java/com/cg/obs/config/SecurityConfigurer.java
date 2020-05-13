package com.cg.obs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.obs.filter.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{


	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().cors().disable()
		.authorizeRequests()
		//.antMatchers(HttpMethod.GET, "/login/authenticate").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST,"/login/authenticate").permitAll()
		.antMatchers(HttpMethod.POST,"/login/Login/add").hasAuthority("USER")
		.antMatchers("/login/getAllLoginCredentials").hasAuthority("ADMIN")
		//.anyRequest().hasAuthority("ADMIN")
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
	