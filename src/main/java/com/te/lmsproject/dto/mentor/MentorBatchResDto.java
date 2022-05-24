package com.te.lmsproject.dto.mentor;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.lmsproject.enums.BatchStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentorBatchResDto {

	private Integer batchNo;
	private Integer batchId;
	private String batchName;
	private List<String> technologies;
	private LocalDate startDate;
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private BatchStatus status;
	private Long batchStrength;

}
