package com.te.lmsproject.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.dto.util.ChangePasswordDto;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.mentor.MockRatings;
import com.te.lmsproject.repository.util.ResponseBody;
import com.te.lmsproject.service.employee.EmployeeService;

@RestController
@RequestMapping("/api/emp")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PutMapping("/employee")
	public ResponseEntity<ResponseBody> updateEmp(@RequestBody AddEmployeeDto employee){
		Employee saveEmp = employeeService.updateEmp(employee);
		if (saveEmp != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data updated", saveEmp), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not updated", null), HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/employee/{empName}")
	public ResponseEntity<ResponseBody> getEmpDetails(@PathVariable String empName) {
		Employee empDetails = employeeService.getEmpDetails(empName);
		if (empDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", empDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/employees")
	public ResponseEntity<ResponseBody> getAllEmpDetails() {
		List<Employee> empDetails = employeeService.getAllEmp();
		if (empDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", empDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/mockratings/{empName}")
	public ResponseEntity<ResponseBody> getMockRating(@PathVariable String empName){
		List<MockRatings> mockDetails = employeeService.getMockDetails(empName);
		if (mockDetails != null) {
			return new ResponseEntity<>(new ResponseBody(false, "mock details fetched", mockDetails), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "mock details not fetched", null),
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/changepassword")
	public ResponseEntity<ResponseBody> changePassword(@RequestBody ChangePasswordDto passwordDTO) {
		String changePassword = employeeService.changePassword(passwordDTO);
		if (changePassword != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Password changed", changePassword), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
		}
	}

}
