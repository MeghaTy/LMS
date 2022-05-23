package com.te.lmsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.dto.AddEmployeeDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.ResponseBody;
import com.te.lmsproject.service.EmployeeService;

@RestController
@RequestMapping("lms/api/public")
public class PublicController {

	@Autowired
	EmployeeService service;
	
	/*
	 * EMployee Registration
	 */
	@PostMapping("/")
	public ResponseEntity<ResponseBody> register(@RequestBody AddEmployeeDto employee) throws Exception {
		Employee addEmployee = service.saveEmp(employee);
		return new ResponseEntity<ResponseBody>(new ResponseBody(false, "Successfully registered employee", addEmployee),
				HttpStatus.OK);
	}

}
