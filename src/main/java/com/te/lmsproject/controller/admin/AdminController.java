package com.te.lmsproject.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.te.lmsproject.repository.util.ResponseBody;
import com.te.lmsproject.service.admin.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	private final String FAILURE ="Failure";
	private final String FETCHED = "Data Fetched Successfully";
	

	@PostMapping("/batch/")
	public ResponseEntity<ResponseBody> addBatch(@RequestBody AddBatchDto batchDTO){
		Batch addBatch = adminService.addBatch(batchDTO);
		if (addBatch != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data inserted", addBatch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/batch/")
	public ResponseEntity<ResponseBody> updateBatch(@RequestBody UpdateBatchDto batchDTO){
		Batch addBatch = adminService.updateBatch(batchDTO);
		if (addBatch != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data updated", addBatch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/batch/{batchId}")
	public ResponseEntity<ResponseBody> deleteBatch(@PathVariable Integer batchId) {
		adminService.deleteBatch(batchId);
		return new ResponseEntity<>(new ResponseBody(false, "Batch deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("/batch/{batchId}")
	public ResponseEntity<ResponseBody> getBatch(@PathVariable Integer batchId) throws Exception {
		Batch mentor = adminService.getBatch(batchId);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, FETCHED, mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/batches")
	public ResponseEntity<ResponseBody> getAllBatch() {
		List<AdminBatchDispalyDto> mentor = adminService.getAllBatch();
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, FETCHED, mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/mentor")
	public ResponseEntity<ResponseBody> addMentor(@RequestBody AddMentorDto addMentor) {
		Mentor mentor = adminService.addMentor(addMentor);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Mentor Added", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/mentor")
	public ResponseEntity<ResponseBody> updateMentor(@RequestBody AddMentorDto addMentor) {
		Mentor mentor = adminService.updateMentor(addMentor);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Mentor updated", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/mentor/{empId}")
	public ResponseEntity<ResponseBody> getBatch(@PathVariable String empId)  {
		Mentor mentor = adminService.getMentor(empId);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, FETCHED, mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/mentors")
	public ResponseEntity<ResponseBody> getAllMentor() {
		List<Mentor> mentor = adminService.geAlltMentor();
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, FETCHED, mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/mentor/{empId}")
	public ResponseEntity<ResponseBody> deleteMentor(@PathVariable String empId) {
		adminService.deleteMentor(empId);
		return new ResponseEntity<>(new ResponseBody(false, "Mentor deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("/requests")
	public ResponseEntity<ResponseBody> getRequest() {
		List<EmployeeRequestDto> request = adminService.getAllRequest();
		if (request != null) {
			return new ResponseEntity<>(new ResponseBody(false, "success", request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/request")
	public ResponseEntity<ResponseBody> approveRequest(@RequestBody RequestApproveDto approve)  {
		List<Employee> request = adminService.approveRequest(approve);
		if (request != null) {
			return new ResponseEntity<>(new ResponseBody(false, "success", request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/reject")
	public ResponseEntity<ResponseBody> rejectRequest(@RequestBody RejectDto reject) {
		List<Request> request = adminService.rejectRequest(reject);
		return new ResponseEntity<>(new ResponseBody(false, "Rejected the request", request), HttpStatus.OK);

	}

	@GetMapping("/mentorname/dropdown")
	public ResponseEntity<ResponseBody> getMentorDropDown() {
		List<DropDownDto> mentorName = adminService.getMentorName();
		if (mentorName != null) {
			return new ResponseEntity<>(new ResponseBody(false, "All mentor name successfully fetched", mentorName),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/batch/dropdwon")
	public ResponseEntity<ResponseBody> getBatchId() {
		List<DropDownDto> batchId = adminService.getBatchId();
		if (batchId != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Drop Down of bacth Ids", batchId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, FAILURE, null), HttpStatus.BAD_REQUEST);
		}
	}

}