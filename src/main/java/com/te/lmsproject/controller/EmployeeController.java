package com.te.lmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.dto.AddEmployeeDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.MockRatings;
import com.te.lmsproject.repository.ResponseBody;
import com.te.lmsproject.service.EmployeeService;



@RestController
@RequestMapping("/api/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PutMapping("/update")
	public ResponseEntity<ResponseBody> updateEmp(@RequestBody AddEmployeeDto employee) throws Exception {
		Employee saveEmp = employeeService.updateEmp(employee);
		if (saveEmp != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data updated", saveEmp), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not updated", null), HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/getEmpDetails/{empName}")
	public ResponseEntity<ResponseBody> getEmpDetails(@PathVariable String empName) throws Exception {
		Employee empDetails = employeeService.getEmpDetails(empName);
		if (empDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", empDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getAllEmpDetails")
	public ResponseEntity<ResponseBody> getAllEmpDetails() throws DataViolationException {
		List<Employee> empDetails = employeeService.getAllEmp();
		if (empDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", empDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/mockratings/{empName}")
	public ResponseEntity<ResponseBody> getMockRating(@PathVariable String empName) throws Exception {
		List<MockRatings> mockDetails = employeeService.getMockDetails(empName);
		if (mockDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "mock details fetched", mockDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "mock details not fetched", null),
					HttpStatus.BAD_REQUEST);
		}
	}

}
