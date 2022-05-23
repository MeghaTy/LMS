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
	public Batch addBatch(AddBatchDto batchDto) throws Exception;
	
	public Batch updateBatch(UpdateBatchDto batch) throws Exception;
	
	public void deleteBatch(Integer id) throws Exception;
	
	public Batch getBatch(Integer id) throws Exception;
	
	public List<AdminBatchDispalyDto> getAllBatch() throws Exception;
	
	public Mentor addMentor(AddMentorDto mentor) throws Exception;
	
	public Mentor updateMentor(AddMentorDto mentor) throws Exception;
	
    public void deleteMentor(String empId) throws DataViolationException;
	
	public Mentor getMentor(String empId) throws Exception;
	
	public List<Mentor> geAlltMentor() throws DuplicateDataException;
	
	public List<EmployeeRequestDto> getAllRequest() throws Exception; 
	
	public List<Employee> approveRequest(RequestApproveDto approve) throws Exception;
	
	public List<Request> rejectRequest(RejectDto dto);

	public List<DropDownDto> getMentorName();

	public List<DropDownDto> getBatchId();





}
