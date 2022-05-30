package com.te.lmsproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.te.lmsproject.dao.UserInfoRepo;
import com.te.lmsproject.dao.admin.MentorDao;
import com.te.lmsproject.dao.admin.RequestDao;
import com.te.lmsproject.dao.employee.EmployeeDao;
import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.entity.admin.Request;
import com.te.lmsproject.entity.employee.Address;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.service.employee.EmployeeServiceImple;
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImpleTest {

	@InjectMocks
	EmployeeServiceImple employeeServiceImple;
	
	@Mock
	private EmployeeDao employeeDao;

	@Mock
	private RequestDao requestDao;

	@Mock
	private UserInfoRepo infoRepo;
	
	@Mock
	private MentorDao mentorDao;


	@Test
	void testSaveEmp() {
		AddEmployeeDto employee= new AddEmployeeDto();
		employee.setEmployeeId("12");
		List<Address> addressInfo= new ArrayList<Address>();
		Address address= new Address();
		address.setEmployeeAddressId(123);
		addressInfo.add(address);
		employee.setAddressInfo(addressInfo);
		Employee emp = new Employee();
		emp.setEmployeeId("12");
		emp.setAddressInfo(addressInfo);
		Request request = new Request();
		request.setEmployeeId("12");
		when(employeeDao.findByEmployeeId(Mockito.any())).thenReturn(null);
		when(employeeDao.save(Mockito.any())).thenReturn(emp);
		when(requestDao.save(Mockito.any())).thenReturn(request);
		Employee employee2= employeeServiceImple.saveEmp(employee);
		assertEquals("12", employee2.getEmployeeId());
	}


	@Test
	void testGetEmpDetails() {
		Employee findByEmpName = new Employee();
		findByEmpName.setEmployeeId("123");
		findByEmpName.setEmployeeName("abc");
		when(employeeDao.findByEmployeeName(Mockito.any())).thenReturn(findByEmpName);
		Employee employee= employeeServiceImple.getEmpDetails("abc");
		assertEquals("123", employee.getEmployeeId());
	}

	@Test
	void testGetAllEmp() {
		List<Employee> list = new ArrayList<Employee>();
		Employee findByEmpName = new Employee();
		findByEmpName.setEmployeeId("123");
		findByEmpName.setEmployeeName("abc");
		list.add(findByEmpName);
		when(employeeDao.findAll()).thenReturn(list);
		List<Employee> list1 =employeeServiceImple.getAllEmp();
		assertEquals("123", list1.get(0).getEmployeeId());
	}
}
