package com.te.lmsproject.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.te.lmsproject.repository.UserInfo;

public class UserDetailsInfo implements UserDetails{
	@Autowired
	UserInfo userInfo;
	

	public UserDetailsInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getAuthorities());
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(authority);
		return authorities;
	}
	

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
