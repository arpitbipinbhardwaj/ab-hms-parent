package com.ab.hms.access.service;

import org.springframework.stereotype.Service;

import com.ab.hms.access.dtos.LoginRequestBodyDto;
import com.ab.hms.access.dtos.UserRegisterDto;

@Service
public class RequestBodyValidationService {

	public void verifyUserRegisrerRequestBody(UserRegisterDto userRegisterDto) {
		//TODO: add request body validation and contraint
	}
	
	public void verifyUserLoginRequestBody(LoginRequestBodyDto loginDto) {
		
	}
}
