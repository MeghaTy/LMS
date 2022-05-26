package com.te.lmsproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.util.ResponseBody;
import com.te.lmsproject.service.employee.EmployeeService;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeControllerTest {

	@MockBean
	private EmployeeService employeeService;

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	

	@Test
	void testGetEmpDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		Employee empDetails = new Employee();
		empDetails.setEmployeeName("abc");
		Mockito.when(employeeService.getEmpDetails("abc")).thenReturn(empDetails);
		String result = mockmvc.perform(get("/api/emp/employee/abc").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsBytes(empDetails))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseBody response = mapper.readValue(result, ResponseBody.class);
		assertEquals("data fetched",response.getMessage());
	}

	@Test
	void testGetAllEmpDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		List<Employee> employees = new ArrayList<Employee>();
		Employee empDetails = new Employee();
		empDetails.setEmployeeName("abc");
		employees.add(empDetails);
		Mockito.when(employeeService.getAllEmp()).thenReturn(employees);
		String result = mockmvc.perform(get("/api/emp/employees").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsBytes(empDetails))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseBody response = mapper.readValue(result, ResponseBody.class);
		assertEquals("data fetched",response.getMessage());
		
	}

	

}
