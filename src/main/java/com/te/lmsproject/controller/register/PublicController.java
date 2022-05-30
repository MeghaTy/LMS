package com.te.lmsproject.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.entity.util.ResponseBody;
import com.te.lmsproject.service.employee.EmployeeService;

@RestController
@RequestMapping("api/public")
public class PublicController {

	@Autowired
	EmployeeService service;
	
	/**
	 * 
	 * @param employee
	 * 
	 * Employee Register
	 */
	@PostMapping("/")
	public ResponseEntity<ResponseBody> register(@RequestBody AddEmployeeDto employee){
		Employee addEmployee = service.saveEmp(employee);
		return new ResponseEntity<>(new ResponseBody(false, "Successfully registered employee", addEmployee),
				HttpStatus.OK);
	}

}
