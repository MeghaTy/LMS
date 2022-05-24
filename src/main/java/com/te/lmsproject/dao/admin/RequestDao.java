package com.te.lmsproject.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.admin.Request;

@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {
	public void deleteByEmployeeIdIn(List<String> empId);

	public List<Request> findByEmployeeIdIn(List<String> ids);

}
