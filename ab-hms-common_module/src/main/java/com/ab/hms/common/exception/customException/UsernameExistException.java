package com.ab.hms.common.exception.customException;

import org.springframework.http.HttpStatus;

import com.ab.hms.common.exception.constant.ExceptionConstants;
import com.ab.hms.common.exception.dtos.ExceptionResponseDto;

public class UsernameExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExceptionResponseDto exceptionResponseDto;
	
	public UsernameExistException(){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.USERNAME_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(ExceptionConstants.USERNAME_EXIST_MESSAGE);
		exceptionResponseDto.setHttpStatus(HttpStatus.CONFLICT);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.CONFLICT.value());
		exceptionResponseDto.setReason(HttpStatus.CONFLICT.getReasonPhrase());
	}
	
	public UsernameExistException(String message){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.USERNAME_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(message);
		exceptionResponseDto.setHttpStatus(HttpStatus.CONFLICT);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.CONFLICT.value());
		exceptionResponseDto.setReason(HttpStatus.CONFLICT.getReasonPhrase());
	}

}
