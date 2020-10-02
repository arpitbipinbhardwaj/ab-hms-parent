package com.ab.hms.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ab.hms.common.constant.SecurityConstants;
import com.ab.hms.common.service.JWTUtilValidationService;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	private JWTUtilValidationService jwtUtilValidationService;
	
	public JWTAuthorizationFilter(
			JWTUtilValidationService jwtUtilValidationService
			) {
		this.jwtUtilValidationService = jwtUtilValidationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase(SecurityConstants.OPTIONS_HTTP_METHOD)) {
			response.setStatus(HttpStatus.OK.value());
		}
		else {
			String authorizationHeader = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
			if(authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}
			String token = authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
			String username = jwtUtilValidationService.getSubject(token);
			if(jwtUtilValidationService.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
				List<GrantedAuthority> authorities = jwtUtilValidationService.getAuthorities(token);
				Authentication authentication = jwtUtilValidationService.getAuthentication(username, authorities, request);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			else {
				SecurityContextHolder.clearContext();
			}
		}
		filterChain.doFilter(request, response);
	}

}
