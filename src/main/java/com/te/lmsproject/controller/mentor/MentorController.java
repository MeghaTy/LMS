package com.te.lmsproject.controller.mentor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.dto.ChangePasswordDto;
import com.te.lmsproject.dto.DropDownDto;
import com.te.lmsproject.dto.mentor.AddMockDto;
import com.te.lmsproject.dto.mentor.AddMockRatingsDto;
import com.te.lmsproject.dto.mentor.AttendanceDto;
import com.te.lmsproject.dto.mentor.EmployeeDisplayInMentorModDto;
import com.te.lmsproject.dto.mentor.EmployeeStatusChangeByMentor;
import com.te.lmsproject.dto.mentor.MentorBatchResDto;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.entity.mentor.Mock;
import com.te.lmsproject.entity.mentor.MockRatings;
import com.te.lmsproject.entity.util.ResponseBody;
import com.te.lmsproject.service.mentor.MentorService;

@RestController
@RequestMapping("/api/mentor")
@CrossOrigin(origins = "*")
public class MentorController {

	@Autowired
	private MentorService mentorService;
	/**
	 * 
	 * @param batchName
	 * 
	 * Fetch Employee details by Batch Name
	 */

	@GetMapping("/employeeByBatchName/{batchName}")
	public ResponseEntity<ResponseBody> getEmpByBatchName(@PathVariable String batchName) {
		List<Employee> empByBatchName = mentorService.getEmpByBatchName(batchName);
		if (empByBatchName != null) {
			return new ResponseEntity<>(new ResponseBody(false, "employee fetched", empByBatchName), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "data not fetched", null), HttpStatus.BAD_REQUEST);
		}

	}
	/**
	 * 
	 * @param mentorId
	 * 
	 * Fetch Batch name with respect to MentorId
	 * 
	 */

	@GetMapping("/batchnamedropdownbymentorid/{mentorId}")
	public ResponseEntity<ResponseBody> getBatchName(@PathVariable String mentorId) {
		List<DropDownDto> batchname = mentorService.getBatchNameByMentor(mentorId);
		return new ResponseEntity<>(new ResponseBody(false, "Batch Name of mentor fetched successfully", batchname),
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param batchId
	 * 
	 * Fetch Employee Details with respect to batchId 
	 * 
	 */
	@GetMapping("/empdetailsbybatchid/{batchId}")
	public ResponseEntity<ResponseBody> getBatchDetailsOfMentor(@PathVariable Integer batchId) {
		List<EmployeeDisplayInMentorModDto> getstatus = mentorService.getstatus(batchId);
		return new ResponseEntity<>(new ResponseBody(false,
				"Employee Details of batch fetched successfully for dashboard drop down", getstatus), HttpStatus.OK);
	}
	

	/**
	 * 
	 * @param empId
	 * 
	 * Fetch Mock Ratings with respect to employeeId
	 * 
	 */
	@GetMapping("/employeeDetails/MockRating/{empId}")
	public ResponseEntity<ResponseBody> getDetailsOfEmployee(@PathVariable String empId) {
		List<MockRatings> details = mentorService.getEmployeeDetails(empId);
		return new ResponseEntity<>(new ResponseBody(false, "Mock Rating of a employee", details), HttpStatus.OK);

	}

	/**
	 * 
	 * @param mentorId
	 * 
	 * Fetch Batch details with respect to mentorId
	 * 
	 */
	@GetMapping("/batchdashboard/{mentorId}")
	public ResponseEntity<ResponseBody> getAllBatchs(@PathVariable String mentorId) {
		List<MentorBatchResDto> allBatch = mentorService.getAllBatch(mentorId);
		return new ResponseEntity<>(new ResponseBody(false, "Mentor Batch Details feteched successfully", allBatch),
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param AddMockDto
	 * 
	 * Create Mock 
	 * 
	 */
	@PostMapping("/mock")
	public ResponseEntity<ResponseBody> addMock(@RequestBody AddMockDto mock) {
		Mock createMock = mentorService.createMock(mock);
		return new ResponseEntity<>(new ResponseBody(false, "Mock created", createMock), HttpStatus.OK);
	}
	
	

	/**
	 * 
	 * @param AddMockRatingsDto
	 * 
	 * Create Mock_Ratings
	 * 
	 */
	@PostMapping("/mockratings")
	public ResponseEntity<ResponseBody> giveMockRatings(@RequestBody AddMockRatingsDto dto) {
		MockRatings giveMockRatings = mentorService.giveMockRatings(dto);
		return new ResponseEntity<>(new ResponseBody(false, "Mock created", giveMockRatings), HttpStatus.OK);
	}

	/**
	 * 
	 * @param attendance
	 * 
	 * Create Attendance
	 * 
	 */
	@PostMapping("/attendance")
	public ResponseEntity<ResponseBody> attendance(@RequestBody AttendanceDto attendance) {
		mentorService.giveAttendance(attendance);
		return new ResponseEntity<>(new ResponseBody(false, "Attendance Updated", attendance), HttpStatus.OK);
	}

	/**
	 * 
	 * @param passwordDTO
	 * 
	 * Employee Password Change
	 *  
	 */
	@PostMapping("/password")
	public ResponseEntity<ResponseBody> changePassword(@RequestBody ChangePasswordDto passwordDTO) {
		String changePassword = mentorService.changePassword(passwordDTO);
		if (changePassword != null) {
			return new ResponseEntity<>(new ResponseBody(false, "Password changed", changePassword), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 
	 * @param EmployeeStatusChangeByMentor
	 * 
	 * Employee Status Change by Mentor
	 * 
	 */
	@PostMapping("/status")
	public ResponseEntity<ResponseBody> mentorChangeEmployeeStatus(@RequestBody EmployeeStatusChangeByMentor dto) {
		 String status = mentorService.mentorChangeEmployeeStatus(dto);
		
		if (status !=null) {
			return new ResponseEntity<>(new ResponseBody(false, "status changed", status), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ResponseBody(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
		}
		
	}

}
