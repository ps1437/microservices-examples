package com.syscho.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.syscho.user.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserService userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	public SecurityConfig(UserService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.addFilter(authenticationFilter());
		http.addFilterBefore(new AuthorizationFilter(authenticationManager(), jwtUtils),
				UsernamePasswordAuthenticationFilter.class);
		http.authorizeRequests().antMatchers("/users/signup", "/h2-console/**").permitAll().anyRequest()
				.authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authMgr) throws Exception {
		authMgr.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

	}

	AuthenticationFilter authenticationFilter() throws Exception {
		AuthenticationFilter filter = new AuthenticationFilter(jwtUtils);
		filter.setFilterProcessesUrl("/users/login");
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

}
