package com.te.lmsproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
	
	public Employee findByEmpId(String empId);
	
	public Employee findByEmpName(String empName);
	
	public List<Employee> findByEmpIdIn(List<String> empId);
}
