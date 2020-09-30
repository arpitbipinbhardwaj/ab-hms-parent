package com.ab.hms.common.exception.dtos;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class ExceptionResponseDto {

	private int httpStatusCode;
	private HttpStatus httpStatus;
	private String errorCode;
	private String reason;
	private String message;
}
