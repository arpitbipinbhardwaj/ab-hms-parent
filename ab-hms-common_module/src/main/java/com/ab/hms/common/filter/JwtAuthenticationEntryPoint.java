package com.ab.hms.common.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.ab.hms.common.constant.ExceptionConstants;
import com.ab.hms.common.constant.SecurityConstants;
import com.ab.hms.common.exception.dtos.ExceptionResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException {

		ExceptionResponseDto httpResponse = new ExceptionResponseDto(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN,
				ExceptionConstants.FORBIDDEN_ERRORCODE, HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(),
				SecurityConstants.FORBIDDEN_MESSAGE);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();

	}
}
