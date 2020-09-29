package com.ab.hms.common.exception.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ab.hms.common.exception.customException.EmailExistException;
import com.ab.hms.common.exception.customException.UsernameExistException;
import com.ab.hms.common.exception.dtos.ExceptionResponseDto;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionController {
	
	@ExceptionHandler(UsernameExistException.class)
	public ResponseEntity<ExceptionResponseDto> usernameExistExceptionHandler(UsernameExistException ex){
		ExceptionResponseDto error = ex.exceptionResponseDto;
		ResponseEntity<ExceptionResponseDto> response = new ResponseEntity<ExceptionResponseDto>(error,error.getHttpStatus());
		log.error("Error occured for existing username");
		return response;
	}

	@ExceptionHandler(EmailExistException.class)
	public ResponseEntity<ExceptionResponseDto> emailExistExceptionHandler(EmailExistException ex){
		ExceptionResponseDto error = ex.exceptionResponseDto;
		ResponseEntity<ExceptionResponseDto> response = new ResponseEntity<ExceptionResponseDto>(error,error.getHttpStatus());
		log.error("Error occured for existing email");
		return response;
	}
}
