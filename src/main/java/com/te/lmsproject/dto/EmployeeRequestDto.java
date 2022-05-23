package com.te.lmsproject.dto;

import com.te.lmsproject.enums.YearOfExp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {

	
	private String employeeId;
	private String employeeName;
	private	int yearOfPassOut;
	private double percentage;
	private YearOfExp experience;
	private String contactNumber;
}