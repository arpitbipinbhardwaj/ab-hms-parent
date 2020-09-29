package com.ab.hms.access.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_LOGIN", schema = "HMSIMPL")
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class UserDetailModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false)
	private Integer id;
	
	@Column(name = "USERNAME", nullable = false, updatable = false, length = 20)
	private String username;
	
	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false, updatable = false, length = 255)
	private String password;
	
	@Column(name = "EMAIL", nullable = false, length = 255)
	private String email;
	
	@Column(name = "ISACTIVE", nullable = false)
	private Boolean isActive;
	
	@Column(name = "ISNOTLOCKED", nullable = false)
	private Boolean isNotLocked;
	
	@Column(name = "ROLE", nullable = false)
	private String role;
	
	@Column(name = "AUTHORITIES", nullable = false, columnDefinition = "TEXT")
	private String authorities;
	
	@Column(name = "PASSWORD_EXPIRY", nullable = false)
	private Date passwordExpiry;
	
	@Column(name = "DEFAULT_PASSWORD", nullable = false)
	private Boolean defaultPassword;

}
