package com.te.lmsproject.dto.mentor;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMockDto {
	@NotNull(message = "Batch Id is missing")
	private Integer batchId;
	@NotNull(message = "Mock Number is missing")
	private Integer mockNo;
	@NotNull(message = "Technology id is missing")
	private Integer techId;
	@NotNull(message = "Employee id of Mentor is missing")
	private List<String> mentorId;
	@NotEmpty(message =  "date cannot not be empty")
	@NotNull(message = "date is missing")
	private LocalDateTime dateTime;
}
