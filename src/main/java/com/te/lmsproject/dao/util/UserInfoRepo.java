package com.te.lmsproject.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.util.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{

	public UserInfo findByUsername(String empId);

}
