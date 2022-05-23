package com.te.lmsproject.service;

import java.util.List;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dto.AddBatchDto;
import com.te.lmsproject.dto.AddMentorDto;
import com.te.lmsproject.dto.AdminBatchDispalyDto;
import com.te.lmsproject.dto.DropDownDto;
import com.te.lmsproject.dto.EmployeeRequestDto;
import com.te.lmsproject.dto.RejectDto;
import com.te.lmsproject.dto.RequestApproveDto;
import com.te.lmsproject.dto.UpdateBatchDto;
import com.te.lmsproject.repository.Batch;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.Mentor;
import com.te.lmsproject.repository.Request;

public interface AdminService {
	public Batch addBatch(AddBatchDto batchDto) ;
	
	public Batch updateBatch(UpdateBatchDto batch);
	
	public void deleteBatch(Integer id);
	
	public Batch getBatch(Integer id);
	
	public List<AdminBatchDispalyDto> getAllBatch();
	
	public Mentor addMentor(AddMentorDto mentor);
	
	public Mentor updateMentor(AddMentorDto mentor);
	
    public void deleteMentor(String empId);
	
	public Mentor getMentor(String empId);
	
	public List<Mentor> geAlltMentor();
	
	public List<EmployeeRequestDto> getAllRequest() ; 
	
	public List<Employee> approveRequest(RequestApproveDto approve);
	
	public List<Request> rejectRequest(RejectDto dto);

	public List<DropDownDto> getMentorName();

	public List<DropDownDto> getBatchId();





}
