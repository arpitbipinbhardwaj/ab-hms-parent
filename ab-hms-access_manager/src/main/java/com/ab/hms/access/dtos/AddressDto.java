package com.ab.hms.access.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AddressDto {
	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String pinCode;
	private String city;
	private String state;

}
