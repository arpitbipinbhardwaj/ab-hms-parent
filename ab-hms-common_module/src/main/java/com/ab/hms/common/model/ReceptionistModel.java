package com.ab.hms.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RECEPTIONIST_INFO", schema = "HMSIMPL")
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class ReceptionistModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Integer id;
	
	@Column(name = "RECEPTIONIST_ID", nullable = false, updatable = false)
	private String receptionist_id;
	
	@Column(name = "USERNAME", nullable = false, updatable = true)
	private String username;
	
	@Column(name = "FIRSTNAME", length = 25)
	private String firstName;
	
	@Column(name = "LASTNAME", length = 30)
	private String lastName;
	
	@Column(name = "DATEOFBIRTH")
	private Date dob;
	
	@Embedded
	private Address address;
	
	@Column(name = "CONTACTNO", length = 10)
	private String contactNumber;
	
	@Column(name = "EMERCENCY_CONTACT", length = 255)
	private String emergencyContact;
	
	@Embedded
	private ActivityLog activityLog;
}
