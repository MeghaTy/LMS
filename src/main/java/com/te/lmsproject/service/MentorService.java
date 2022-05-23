package com.te.lmsproject.service;

import java.util.List;

import com.te.lmsproject.dto.AddMockDto;
import com.te.lmsproject.dto.AddMockRatingsDto;
import com.te.lmsproject.dto.AttendanceDto;
import com.te.lmsproject.dto.ChangePasswordDto;
import com.te.lmsproject.dto.DropDownDto;
import com.te.lmsproject.dto.EmployeeDisplayInMentorModDto;
import com.te.lmsproject.dto.MentorBatchResDto;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.Mock;
import com.te.lmsproject.repository.MockRatings;

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


	
}
