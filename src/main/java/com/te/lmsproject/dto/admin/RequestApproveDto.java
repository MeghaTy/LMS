package com.te.lmsproject.dto.admin;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestApproveDto {
	@NotEmpty(message = "batchId cannot not be empty")
	@NotNull(message = "batchId is missing")
	private int batchId;
	@NotEmpty(message = "batchName cannot not be empty")
	@NotNull(message = "batchName is missing")
	private String batchName;
	@NotEmpty(message = "employeesId cannot not be empty")
	@NotNull(message = "employeesId is missing")
	private List<String> employeesId;

}
