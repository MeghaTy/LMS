package com.te.lmsproject.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.te.lmsproject.enums.EmplyoeeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDisplayInMentorModDto {

	
	private String empId;
	private String empName;
	private int mocksTaken;
	private int attendance;
	@Enumerated(EnumType.STRING)
	private EmplyoeeStatus status;

}
