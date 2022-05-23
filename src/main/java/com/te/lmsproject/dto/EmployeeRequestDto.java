package com.te.lmsproject.dto;

import com.te.lmsproject.enums.YearOfExp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {

	
	private String empId;
	private String empName;
	private	int empYearOfPassOut;
	private double empPercentage;
	private YearOfExp experience;
	private String contactNo;
}