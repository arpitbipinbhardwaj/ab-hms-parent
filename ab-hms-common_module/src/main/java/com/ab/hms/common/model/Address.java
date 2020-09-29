package com.ab.hms.common.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Address {
	
	@Column(name = "ADDRESSLINE1", length = 50)
	private String addressLine1;
	
	@Column(name = "ADDRESSLINE2", length = 50)
	private String addressLine2;
	
	@Column(name = "ADDRESSLINE3", length = 50)
	private String addressLine3;
	
	@Column(name = "PINCODE", length = 50)
	private String pinCode;
	
	@Column(name = "CITY", length = 50)
	private String city;
	
	@Column(name = "STATE", length = 50)
	private String state;

}
