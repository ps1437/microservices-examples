package com.syscho.auth.config.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthDetails extends UserDO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8182665701622678664L;

	public UserAuthDetails(UserDO user) {

		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority("READ"));
		if("admin".equalsIgnoreCase(getUsername())) {
		  grantedAuthorities.add(new SimpleGrantedAuthority("WRITE"));
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {

		return super.getPassword();
	}

	@Override
	public String getUsername() {

		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {

		return super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {

		return super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {

		return super.isEnabled();
	}
}
