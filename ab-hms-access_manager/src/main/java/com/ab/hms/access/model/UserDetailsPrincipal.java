package com.ab.hms.access.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsPrincipal implements UserDetails{

	
	private UserDetailModel user;
	
	public UserDetailsPrincipal(UserDetailModel user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		Stream<String> stream = Arrays.stream(this.user.getAuthorities().split(","));
		return stream.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public String getPassword() {
		return this.user.getPassword();
	}

	public String getUsername() {
		return this.user.getUsername();
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		return this.user.getIsNotLocked();
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		return this.user.getIsActive();
	}

}
