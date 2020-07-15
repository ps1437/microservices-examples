package com.syscho.auth.config.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity

@Table(name = "user")
@Data
public class UserDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -414398720609838309L;

	public UserDO() {
	}

	public UserDO(UserDO user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.enabled = user.isEnabled();
		this.accountNonExpired = user.isAccountNonExpired();
		this.credentialsNonExpired = user.isCredentialsNonExpired();
		this.accountNonLocked = user.isAccountNonLocked();
	}

	
	public UserDO(Integer id, String username, String password, String email, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "accountNonExpired")
	private boolean accountNonExpired;
	@Column(name = "credentialsNonExpired")
	private boolean credentialsNonExpired;
	@Column(name = "accountNonLocked")
	private boolean accountNonLocked;

}
