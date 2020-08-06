package com.syscho.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.syscho.user.service.UserService;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	JwtUtils jwtUtils;

	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		super(authenticationManager);
		this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String jwtToken = null;
		String username = null;

		final String authTokenHeader = req.getHeader("Authorization");

		if (!Objects.nonNull(authTokenHeader) || !authTokenHeader.startsWith("Bearer ")) {
			chain.doFilter(req, resp);
			return;
		}

		jwtToken = authTokenHeader.substring(7);
		username = jwtUtils.extaractUserName(jwtToken);

		if (Objects.nonNull(username) && !Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {

			if (jwtUtils.validateToken(jwtToken, username)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
						null, new ArrayList<>());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(req, resp);

	}

}
