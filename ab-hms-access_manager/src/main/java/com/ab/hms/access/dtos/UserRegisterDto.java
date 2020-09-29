package com.ab.hms.access.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UserRegisterDto {
	
	private String username;
	private String email;
	private Boolean isActive;
	private Boolean isNotLocked;
	private String role;
		
}
