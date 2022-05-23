package com.te.lmsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.te.lmsproject.dao.UserInfoRepo;
import com.te.lmsproject.dto.UserDetailsInfo;

@Service
public class MyUserDetialsService implements UserDetailsService{
	
	@Autowired
	UserInfoRepo userInfoRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserDetailsInfo(userInfoRepo.findByUsername(username));
	}

}
