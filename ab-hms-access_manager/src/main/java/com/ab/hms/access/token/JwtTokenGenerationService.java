package com.ab.hms.access.token;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.ab.hms.access.model.UserDetailsPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ab.hms.common.constant.SecurityConstants;

@Service
public class JwtTokenGenerationService {

public String generateJwtToken(UserDetailsPrincipal userPrincipal) {
		
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create().withIssuer(SecurityConstants.ISSUER).withAudience(SecurityConstants.HMS_ADMINISTRATION)
				.withIssuedAt(new Date()).withSubject(userPrincipal.getUsername()).withArrayClaim(SecurityConstants.AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.JWT_TOKEN_SECRET.getBytes()));
	}
	
	private String[] getClaimsFromUser(UserDetailsPrincipal user) {
		
		List<String> authorities = new ArrayList<>();
		for(GrantedAuthority grantedAuthority: user.getAuthorities()) {
			authorities.add(grantedAuthority.getAuthority());
		}
		return authorities.toArray(new String[0]);
	}
}
