package com.ab.hms.access.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_ROLE", schema = "HMSIMPL")
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class UserRoleModel {
	
	@Id
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "ROLENAME", nullable = false)
	private String roleName;
	
	@Column(name = "AUTHORITIES", nullable = false, columnDefinition = "TEXT")
	private String authorities;

}
