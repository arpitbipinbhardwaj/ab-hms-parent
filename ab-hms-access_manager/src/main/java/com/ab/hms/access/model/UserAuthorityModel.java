package com.ab.hms.access.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_AUTHORITY", schema = "HMSIMPL")
@Data@AllArgsConstructor@NoArgsConstructor
public class UserAuthorityModel {
	
	@Id
	@Column(name = "ID", nullable = false, insertable = false, updatable = false)
	private Integer id;
	
	@Column(name = "MODULE", nullable = false, insertable = false, updatable = false)
	private String module;
	

	@Column(name = "AUTHORITY", nullable = false, insertable = false, updatable = false)
	private String authority;

}
