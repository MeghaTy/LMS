package com.te.lmsproject.service;

import java.util.List;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.dto.AddEmployeeDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.MockRatings;

public interface EmployeeService {
	public Employee saveEmp(AddEmployeeDto employee); 
	
	public Employee updateEmp(AddEmployeeDto addEmployee);

	public Employee getEmpDetails(String empName);

	public List<Employee> getAllEmp();
	
	public List<MockRatings> getMockDetails(String empName);
	
	

}
