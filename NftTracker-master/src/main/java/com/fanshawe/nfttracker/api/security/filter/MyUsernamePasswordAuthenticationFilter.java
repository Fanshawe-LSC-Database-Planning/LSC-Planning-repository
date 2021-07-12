package com.fanshawe.nfttracker.api.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.request.ApiUserRequest;
import com.fanshawe.nfttracker.api.security.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ServletInputStream sip = request.getInputStream();
			ApiUserRequest user = new ObjectMapper().readValue(sip, ApiUserRequest.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		ApiUser usr = ((ApiUser) authResult.getPrincipal());

		String token = Jwts.builder().setSubject(usr.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.JWT_TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.TOKEN_SECRET).compact();
		response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOEKN_PREFIX + token);
	}
}
