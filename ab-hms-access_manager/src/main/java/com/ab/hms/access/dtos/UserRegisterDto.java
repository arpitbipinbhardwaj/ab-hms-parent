package com.ab.hms.access.dtos;

import com.ab.hms.common.customconstraint.ValidDate;

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
	
	private String firstName;
	private String lastName;
	
	@ValidDate
	private String dob;
	private AddressDto address;
	private String contactNumber;
	private String emergencyContact;
		
}
