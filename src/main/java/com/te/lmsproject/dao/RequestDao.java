package com.te.lmsproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.Request;

@Repository
public interface RequestDao extends JpaRepository<Request, Integer> {
	public void deleteByEmpIdIn(List<String> empId);

	public List<Request> findByEmpIdIn(List<String> ids);

}
