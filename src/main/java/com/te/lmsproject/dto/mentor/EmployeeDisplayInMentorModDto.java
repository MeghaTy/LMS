package com.te.lmsproject.dto.mentor;

import java.util.List;
import com.te.lmsproject.repository.mentor.MockRatings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDisplayInMentorModDto {

	
	private String employeeId;
	private String employeeName;
	private int mocksTaken;
	private int attendance;
	private String status;
	private List<MockRatings> mockRatings;

}
