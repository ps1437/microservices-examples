package com.syscho.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USERS")
public class UserDO {
	
	@Id
	@Column(name = "USER_ID", nullable = false, unique = true)
	private String userName;

	@Column(name = "EMAIL_ID", nullable = false, unique = true)
	private String emailId;

	@Column(name = "PASSWORD", nullable = false)
	private String encryptedPwd;

	@Column(name = "BIRTH_DATE", nullable = false)
	private String dob;

	@Column(name = "GENDER")
	private String gender;

}