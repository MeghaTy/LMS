package com.te.lmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{

	UserInfo findByUsername(String empId);

}
