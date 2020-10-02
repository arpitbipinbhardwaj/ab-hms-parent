package com.ab.hms.common.constant;

public class SecurityConstants {

	public static final String JWT_TOKEN_SECRET = "gdkoo((98.14+=";
	
	public static final long EXPIRATION_TIME = 1000*60*60*24; //miilsec*sec*min*hr
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_HEADER = "AUTH-TOKEN";
	
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token Cannot Be Verfied";
	public static final String ISSUER = "Hospital Managermrnt System";
	public static final String HMS_ADMINISTRATION = "User Management Portal";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to login to access this page"; 
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	
	public static final String[] PUBLIC_URL = {
			"/register",
			"/login",
			"/user/resetpassword/**",
			"/user/image/**",
			"/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**"
	};
}
