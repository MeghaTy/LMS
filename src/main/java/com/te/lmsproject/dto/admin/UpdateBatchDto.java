package com.te.lmsproject.dto.admin;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.te.lmsproject.enums.BatchStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBatchDto {
	@NotEmpty(message = "batchId cannot not be empty")
	@NotNull(message = "batchId is missing")
	private int batchId;
	@NotEmpty(message = "batchName cannot not be empty")
	@NotNull(message = "batchName is missing")
	private String batchName;
	@NotEmpty(message = "mentorName cannot not be empty")
	@NotNull(message = "mentorName is missing")
	private String mentorName;
	@NotEmpty(message = "Technical id cannot not be empty")
	@NotNull(message = "Technical id is missing")
	private List<Integer> technicalId;
	@NotEmpty(message = "startDate cannot not be empty")
	@NotNull(message = "startDate is missing")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	@NotEmpty(message = "endDate cannot not be empty")
	@NotNull(message = "endDate is missing")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;
	@NotEmpty(message = "status cannot not be empty")
	@NotNull(message = "status is missing")
	private BatchStatus status;

}
