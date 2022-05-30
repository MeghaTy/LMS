package com.te.lmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lmsproject.entity.util.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{

	public UserInfo findByUsername(String empId);

}
