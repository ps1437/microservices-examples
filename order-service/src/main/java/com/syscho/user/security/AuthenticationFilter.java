package com.syscho.user.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syscho.user.vo.LoginVO;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtils jwtUtils;

	public AuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LoginVO loginReq = null;
		try {
			loginReq = new ObjectMapper().readValue(request.getInputStream(), LoginVO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = jwtUtils.createToken(auth.getPrincipal().toString());
		response.setHeader("token", token);

	}
}
