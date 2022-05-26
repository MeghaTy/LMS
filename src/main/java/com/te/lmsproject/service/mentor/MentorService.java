package com.te.lmsproject.service.mentor;

import java.util.List;

import com.te.lmsproject.dto.mentor.AddMockDto;
import com.te.lmsproject.dto.mentor.AddMockRatingsDto;
import com.te.lmsproject.dto.mentor.AttendanceDto;
import com.te.lmsproject.dto.mentor.EmployeeDisplayInMentorModDto;
import com.te.lmsproject.dto.mentor.EmployeeStatusChangeByMentor;
import com.te.lmsproject.dto.mentor.MentorBatchResDto;
import com.te.lmsproject.dto.util.ChangePasswordDto;
import com.te.lmsproject.dto.util.DropDownDto;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.mentor.Mock;
import com.te.lmsproject.repository.mentor.MockRatings;

public interface MentorService {

	public List<Employee> getEmpByBatchName(String batchName);

	public List<MockRatings> getEmployeeDetails(String empId);
	
	public List<DropDownDto> getBatchNameByMentor(String mentorId);
	
	public List<EmployeeDisplayInMentorModDto> getstatus(Integer batchId);
	
	public Mock createMock(AddMockDto mock);
	
	public MockRatings giveMockRatings(AddMockRatingsDto ratings);
	
	public List<MentorBatchResDto> getAllBatch(String mentorId);
	
	public void giveAttendance(AttendanceDto attendance);

	public String changePassword(ChangePasswordDto dto);

	public String  mentorChangeEmployeeStatus(EmployeeStatusChangeByMentor dto);

	
}
