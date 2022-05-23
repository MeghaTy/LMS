package com.te.lmsproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dao.EmployeeDao;
import com.te.lmsproject.dao.RequestDao;
import com.te.lmsproject.dto.AddEmployeeDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.MockRatings;
import com.te.lmsproject.repository.Request;

@Service
public class EmployeeServiceImple implements EmployeeService {

	@Autowired
	private EmployeeDao empDao;

	@Autowired
	private RequestDao requestDao;


	@Override
	@Transactional
	public Employee saveEmp(AddEmployeeDto employee){
		if(empDao.findByEmployeeId(employee.getEmployeeId())==null){
		Employee emp = new Employee();
		BeanUtils.copyProperties(employee, emp);
		emp.setAddressInfo(employee.getAddressInfo());
		Employee save = empDao.save(emp);
		Request request = new Request();
		request.setEmployeeId(emp.getEmployeeId());
		requestDao.save(request);
		return save;
		}else {
			throw new DuplicateDataException("duplicate entry");
		}

	}

	@Override
	public Employee updateEmp(AddEmployeeDto addEmployee) {
		Employee employee = empDao.findByEmployeeId(addEmployee.getEmployeeId());
		if(employee==null) {
			throw new DataViolationException("Employee doess not exist");
		}
		BeanUtils.copyProperties(addEmployee, employee);
		empDao.save(employee);
		return employee;
	}

	@Override
	public Employee getEmpDetails(String empName){
		Employee findByEmpName = empDao.findByEmployeeName(empName);
		if(findByEmpName==null) {
			throw new DataViolationException("Employee does not exist");
		}
		
		return findByEmpName;
	}

	

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> findAll = empDao.findAll();
		if(findAll.isEmpty()) {
			throw new DataViolationException("Employee Details are unavaliable");
		}
		return findAll;
	}

	@Override
	public List<MockRatings> getMockDetails(String empName){
		Employee findByEmpName = empDao.findByEmployeeName(empName);
		if(findByEmpName==null) {
			throw new DataViolationException("Employee does not exist");
		}
		List<MockRatings> mockDetails = findByEmpName.getMockDetails();
		if(mockDetails.isEmpty()) {
			throw new DataViolationException("Employee does not contain any mock details");
		}
		
		return mockDetails;
		
	}

}
