package com.te.lmsproject.dto.admin;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.te.lmsproject.entity.util.Technologies;
import com.te.lmsproject.enums.BatchStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminBatchDispalyDto {
	@NotNull(message = "Batch Id is missing")
	@NotEmpty(message = "Batch Id cannot be empty")
	private Integer batchId;
	@NotNull(message = "BatchName is  missing")
	@NotEmpty(message = "BatchName cannot be empty")
	private String batchName;
	@NotNull(message = "Mentor Name is  missing")
	@NotEmpty(message = "Mentor Name cannot be empty")
	private String mentorName;
	@NotNull(message = "Technology is  missing")
	@NotEmpty(message = "Technology cannot be empty")
	private List<Technologies> technologies;
	@NotNull(message = "startDate is  missing")
	@NotEmpty(message = "startDate cannot be empty")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;
	@NotNull(message = "endDate is  missing")
	@NotEmpty(message = "endDate cannot be empty")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;
	@NotNull(message = "status is  missing")
	@NotEmpty(message = "status cannot be empty")
	private BatchStatus status;

}
