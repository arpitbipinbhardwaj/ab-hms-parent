package com.ab.hms.common.exception.customException;

import org.springframework.http.HttpStatus;

import com.ab.hms.common.constant.ExceptionConstants;
import com.ab.hms.common.exception.dtos.ExceptionResponseDto;

public class EmailExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExceptionResponseDto exceptionResponseDto;
	
	public EmailExistException(){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.EMAIL_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(ExceptionConstants.EMAIL_EXIST_MESSAGE);
		exceptionResponseDto.setHttpStatus(HttpStatus.CONFLICT);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.CONFLICT.value());
		exceptionResponseDto.setReason(HttpStatus.CONFLICT.getReasonPhrase());
	}
	
	public EmailExistException(String message){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.EMAIL_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(message);
		exceptionResponseDto.setHttpStatus(HttpStatus.CONFLICT);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.CONFLICT.value());
		exceptionResponseDto.setReason(HttpStatus.CONFLICT.getReasonPhrase());
	}
}
