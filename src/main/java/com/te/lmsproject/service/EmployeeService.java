package com.te.lmsproject.service;

import java.util.List;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.dto.AddEmployeeDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.MockRatings;

public interface EmployeeService {
	public Employee saveEmp(AddEmployeeDto employee) throws Exception; 
	
	public Employee updateEmp(AddEmployeeDto addEmployee) throws Exception;

	public Employee getEmpDetails(String empName) throws Exception;

	public List<Employee> getAllEmp() throws DataViolationException;
	
	public List<MockRatings> getMockDetails(String empName) throws Exception;
	
	

}
