package com.ab.hms.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ActivityLog {

	@Column(name = "LASTLOGINDATE")
	private Date lastLoginDate;
	
	@Column(name = "LASTLOGINDISPLAYDATE")
	private Date lastLoginDisplayDate;
	
	@Column(name = "JOINDATE")
	private Date joindate;
}
