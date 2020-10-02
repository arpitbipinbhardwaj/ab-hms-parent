package com.ab.hms.common.exception.customException;

import org.springframework.http.HttpStatus;

import com.ab.hms.common.constant.ExceptionConstants;
import com.ab.hms.common.exception.dtos.ExceptionResponseDto;

public class RoleNotExistException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	public ExceptionResponseDto exceptionResponseDto;
	
	public RoleNotExistException(){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.ROLE_NOT_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(ExceptionConstants.ROLE_NOT_EXIST_MESSAGE);
		exceptionResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		exceptionResponseDto.setReason(HttpStatus.BAD_REQUEST.getReasonPhrase());
	}
	
	public RoleNotExistException(String message){
		exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.setErrorCode(ExceptionConstants.ROLE_NOT_EXIST_ERRORCODE);
		exceptionResponseDto.setMessage(message);
		exceptionResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
		exceptionResponseDto.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
		exceptionResponseDto.setReason(HttpStatus.BAD_REQUEST.getReasonPhrase());
	}

}
