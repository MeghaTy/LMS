package com.te.lmsproject.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {

	@NotEmpty(message = "Date cannot not be empty")
	@NotNull(message = "Date is missing")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	private boolean morning;

	private boolean afternoon;

	@NotNull(message = "Employee id cannot be empty")
	private String employeeId;

}
