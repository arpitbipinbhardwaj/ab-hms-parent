package com.ab.hms.access.dtos;

import java.util.Date;

import com.ab.hms.common.model.ActivityLog;
import com.ab.hms.common.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

	private String username;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private String role;
	private Address address;
	private ActivityLog activityLog;
	private Boolean isActive;
	private Date passwordExpiry;
	private String token;
}
