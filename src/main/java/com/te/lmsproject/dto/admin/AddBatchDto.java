package com.te.lmsproject.dto.admin;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddBatchDto {
	private int batchId;
	@NotNull(message = "BatchName is  missing")
	@NotEmpty(message = "BatchName cannot be empty")
	private String batchName;
	@NotNull(message = "Mentor name is missing")
	private String mentorName;
	@NotNull(message = "Technology is missing")
	private List<Integer> technicalId;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotEmpty(message =  "Start date cannot not be empty")
	@NotNull(message = "Start date is missing")
	private LocalDate startDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotEmpty(message =  "Start date cannot not be empty")
	@NotNull(message = "Start date is missing")
	private LocalDate endDate;

}
