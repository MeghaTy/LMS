package com.te.lmsproject.dto.admin;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RejectDto {
	@NotEmpty(message = "reason cannot not be empty")
	@NotNull(message = "reason is missing")
	private String reason;
	@NotEmpty(message = "Employee Id cannot not be empty")
	@NotNull(message = "Employee Id is missing")
	private List<String> employeeIds;

}
