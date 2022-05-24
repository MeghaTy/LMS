package com.te.lmsproject.service.admin;

import java.util.List;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dto.admin.AddBatchDto;
import com.te.lmsproject.dto.admin.AddMentorDto;
import com.te.lmsproject.dto.admin.AdminBatchDispalyDto;
import com.te.lmsproject.dto.admin.EmployeeRequestDto;
import com.te.lmsproject.dto.admin.RejectDto;
import com.te.lmsproject.dto.admin.RequestApproveDto;
import com.te.lmsproject.dto.admin.UpdateBatchDto;
import com.te.lmsproject.dto.util.DropDownDto;
import com.te.lmsproject.repository.admin.Batch;
import com.te.lmsproject.repository.admin.Mentor;
import com.te.lmsproject.repository.admin.Request;
import com.te.lmsproject.repository.employee.Employee;

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
