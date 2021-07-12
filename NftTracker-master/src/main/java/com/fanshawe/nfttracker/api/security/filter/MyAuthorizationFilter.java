package com.fanshawe.nfttracker.api.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fanshawe.nfttracker.api.security.SecurityConstant;

import io.jsonwebtoken.Jwts;

public class MyAuthorizationFilter extends BasicAuthenticationFilter {

	public MyAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstant.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstant.TOEKN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken token = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(token);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstant.HEADER_STRING).replace(SecurityConstant.TOEKN_PREFIX, "")
				.trim();
		String username = Jwts.parser().setSigningKey(SecurityConstant.TOKEN_SECRET).parseClaimsJws(token).getBody()
				.getSubject();

		if (username != null) {
			return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
		}

		return null;
	}

}