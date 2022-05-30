package com.te.lmsproject.service.employee;

import java.util.List;

import com.te.lmsproject.dto.ChangePasswordDto;
import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.entity.mentor.MockRatings;

public interface EmployeeService {
	public Employee saveEmp(AddEmployeeDto employee); 
	
	public Employee updateEmp(AddEmployeeDto addEmployee);

	public Employee getEmpDetails(String empName);

	public List<Employee> getAllEmp();
	
	public List<MockRatings> getMockDetails(String empName);

	public String changePassword(ChangePasswordDto passwordDTO);
	
	

}
