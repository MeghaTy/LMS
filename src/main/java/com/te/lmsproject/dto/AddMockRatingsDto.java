package com.te.lmsproject.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.te.lmsproject.enums.MockType;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddMockRatingsDto {

	@NotNull(message = "Mock Type is missing")
	@NotEmpty(message = "MockType cannot be empty")
	@Enumerated(EnumType.STRING)
	private MockType mockType;

	@NotNull(message = " Employee ID is missing")
	private String employeeId;

	@NotNull(message = "Mock Type is missing")
	private String mockTakenBy;
	
	@NotNull(message = "Tech id is missing")
	private Integer technicalId;
	
	@NotNull(message = "Practical mask is missing")
	@Size(min = 0, max = 100)
	private double practical;
	
	@NotNull(message = "therotical marks is missing")
	@Size(min = 0, max = 100)
	private double therotical;
	
	@NotNull
	@NotNull(message = "Overall marks is missing")
	@Size(min = 0, max = 100)
	private double overAll;
	
	@NotNull(message = "Detailed feedback is missing")
	@NotEmpty(message = "Detailed feedback cannot be empty")
	private String detailedFeedBack;


	
	
}
