package com.te.lmsproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.te.lmsproject.repository.ResponseBody;
import com.te.lmsproject.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/batch/")
	public ResponseEntity<ResponseBody> addBatch(@RequestBody AddBatchDto batchDTO) throws Exception {
		Batch addBatch = adminService.addBatch(batchDTO);
		if (addBatch != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data inserted", addBatch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not inserted", null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/batch/update")
	public ResponseEntity<ResponseBody> updateBatch(@RequestBody UpdateBatchDto batchDTO) throws Exception {
		Batch addBatch = adminService.updateBatch(batchDTO);
		if (addBatch != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data updated", addBatch), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not updated", null), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/batch/{batchId}")
	public ResponseEntity<ResponseBody> deleteBatch(@PathVariable Integer batchId) throws Exception {
		adminService.deleteBatch(batchId);
		return new ResponseEntity<>(new ResponseBody(false, "Batch deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("/getBatch/{batchId}")
	public ResponseEntity<ResponseBody> getBatch(@PathVariable Integer batchId) throws Exception {
		Batch mentor = adminService.getBatch(batchId);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getAllBatch")
	public ResponseEntity<ResponseBody> getAllBatch() throws Exception {
		List<AdminBatchDispalyDto> mentor = adminService.getAllBatch();
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/mentor")
	public ResponseEntity<ResponseBody> addMentor(@RequestBody AddMentorDto addMentor) throws Exception {
		Mentor mentor = adminService.addMentor(addMentor);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Mentor Added", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "Mentor not Added", null), HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/mentor/update")
	public ResponseEntity<ResponseBody> updateMentor(@RequestBody AddMentorDto addMentor) throws Exception {
		Mentor mentor = adminService.updateMentor(addMentor);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Mentor updated", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "Mentor not updated", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getMentor/{empId}")
	public ResponseEntity<ResponseBody> getBatch(@PathVariable String empId) throws Exception {
		Mentor mentor = adminService.getMentor(empId);
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/getAllMentor")
	public ResponseEntity<ResponseBody> getAllMentor() throws DuplicateDataException {
		List<Mentor> mentor = adminService.geAlltMentor();
		if (mentor != null) {
			return new ResponseEntity<>(new ResponseBody(false, "data fetched", mentor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/mentor/{empId}")
	public ResponseEntity<ResponseBody> deleteMentor(@PathVariable String empId) throws DataViolationException {
		adminService.deleteMentor(empId);
		return new ResponseEntity<>(new ResponseBody(false, "Mentor deleted successfully", null), HttpStatus.OK);
	}

	@GetMapping("/request")
	public ResponseEntity<ResponseBody> getRequest() throws Exception {
		List<EmployeeRequestDto> request = adminService.getAllRequest();
		if (request != null) {
			return new ResponseEntity<>(new ResponseBody(false, "success", request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "failure", null), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/approverequest")
	public ResponseEntity<ResponseBody> approveRequest(@RequestBody RequestApproveDto approve) throws Exception {
		List<Employee> request = adminService.approveRequest(approve);
		if (request != null) {
			return new ResponseEntity<>(new ResponseBody(false, "success", request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "failure", null), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/reject")
	public ResponseEntity<ResponseBody> rejectRequest(@RequestBody RejectDto reject) {
		List<Request> request = adminService.rejectRequest(reject);
		return new ResponseEntity<>(new ResponseBody(false, "Rejected the request", request), HttpStatus.OK);

	}

	@GetMapping("/mentorname")
	public ResponseEntity<ResponseBody> getMentorDropDown() {
		List<DropDownDto> mentorName = adminService.getMentorName();
		if (mentorName != null) {
			return new ResponseEntity<>(new ResponseBody(false, "All mentor name successfully fetched", mentorName),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "failure", null), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/batchid")
	public ResponseEntity<ResponseBody> getBatchId() {
		List<DropDownDto> batchId = adminService.getBatchId();
		if (batchId != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Drop Down of bacth Ids", batchId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "failure", null), HttpStatus.BAD_REQUEST);
		}
	}

}