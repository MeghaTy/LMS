package com.te.lmsproject.service.employee;

import java.util.List;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.dto.util.ChangePasswordDto;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.mentor.MockRatings;

public interface EmployeeService {
	public Employee saveEmp(AddEmployeeDto employee); 
	
	public Employee updateEmp(AddEmployeeDto addEmployee);

	public Employee getEmpDetails(String empName);

	public List<Employee> getAllEmp();
	
	public List<MockRatings> getMockDetails(String empName);

	public String changePassword(ChangePasswordDto passwordDTO);
	
	

}
