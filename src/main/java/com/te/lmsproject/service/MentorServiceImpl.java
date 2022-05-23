package com.te.lmsproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.dao.AttendaceDao;
import com.te.lmsproject.dao.BatchDao;
import com.te.lmsproject.dao.EmployeeDao;
import com.te.lmsproject.dao.MentorDao;
import com.te.lmsproject.dao.MockRatingDao;
import com.te.lmsproject.dao.TechnologyDao;
import com.te.lmsproject.dao.UserInfoRepo;
import com.te.lmsproject.dto.AddMockDto;
import com.te.lmsproject.dto.AddMockRatingsDto;
import com.te.lmsproject.dto.AttendanceDto;
import com.te.lmsproject.dto.ChangePasswordDto;
import com.te.lmsproject.dto.DropDownDto;
import com.te.lmsproject.dto.EmployeeDisplayInMentorModDto;
import com.te.lmsproject.dto.MentorBatchResDto;
import com.te.lmsproject.repository.Attendance;
import com.te.lmsproject.repository.Batch;
import com.te.lmsproject.repository.Employee;
import com.te.lmsproject.repository.Mentor;
import com.te.lmsproject.repository.Mock;
import com.te.lmsproject.repository.MockRatings;
import com.te.lmsproject.repository.Technologies;
import com.te.lmsproject.repository.UserInfo;

@Service
public class MentorServiceImpl implements MentorService {

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private TechnologyDao technologyDao;

	@Autowired
	private MentorDao mentorDao;

	@Autowired
	private AttendaceDao attendaceDao;
	
	@Autowired
	private MockRatingDao ratingDao;

	@Autowired
	private UserInfoRepo infoRepo;

	@Override
	public List<Employee> getEmpByBatchName(String batchName) {
		Batch findByBatchName = batchDao.findByBatchName(batchName);
		return findByBatchName.getEmployee();
	}

	@Override
	public List<MockRatings> getEmployeeDetails(String empId) {

		Employee empDetails = employeeDao.findByEmpId(empId);
		if(empDetails==null) {
			throw new RuntimeException();
		}
		return empDetails.getMockDetails();
	}

	@Override
	public List<DropDownDto> getBatchNameByMentor(String mentorId) {
		Mentor mentor = mentorDao.findByEmpId(mentorId);
		List<Batch> batchDetails = mentor.getBatch();
		List<DropDownDto> dropDown = new ArrayList<>();
		for (Batch b : batchDetails) {
			DropDownDto response = new DropDownDto();
			response.setId(b.getId());
			response.setName(b.getBatchName());
			dropDown.add(response);
		}
		return dropDown;
	}

	@Override
	public List<EmployeeDisplayInMentorModDto> getstatus(Integer batchId) {
		Batch batchDetails = batchDao.findByBatchId(batchId);
		List<Employee> employee = batchDetails.getEmployee();
		List<EmployeeDisplayInMentorModDto> displayInMentorMod = new ArrayList<EmployeeDisplayInMentorModDto>();
		for (Employee emp : employee) {
			EmployeeDisplayInMentorModDto mod = new EmployeeDisplayInMentorModDto();
			mod.setEmpId(emp.getEmpId());
			mod.setEmpName(emp.getEmpName());
			mod.setMocksTaken(emp.getMockDetails().size());
			mod.setStatus(emp.getEmpStatus());
			mod.setAttendance(emp.getAttendances().size());
			displayInMentorMod.add(mod);
		}

		return displayInMentorMod;
	}

	@Override
	public Mock createMock(AddMockDto mockdetails) {
		Mock mock = new Mock();
		mock.setMockNo(mockdetails.getMockNo());

		Technologies technologies = technologyDao.findById(mockdetails.getTechId()).get();
		mock.setTech(technologies);

		List<Mentor> mentorDetails = mentorDao.findByEmpIdIn(mockdetails.getMentorId());
		mock.setMentor(mentorDetails);

		mock.setDate(mockdetails.getDateTime());

		Batch batchDetails = batchDao.findByBatchId(mockdetails.getBatchId());
		List<Mock> mock2 = batchDetails.getMock();
		mock2.add(mock);
		batchDao.save(batchDetails);
		return mock;
	}

	@Override
	public List<MentorBatchResDto> getAllBatch(String mentorId) {
		Mentor mentor = mentorDao.findByEmpId(mentorId);
		List<Batch> mentorBatchDetails = batchDao.findByMentor(mentor);
		List<MentorBatchResDto> arrayList = new ArrayList<>();
		mentorBatchDetails.stream().forEach(m -> {
			MentorBatchResDto mentorBatchResDto = new MentorBatchResDto();
			mentorBatchResDto.setBatchId(m.getBatchId());
			mentorBatchResDto.setBatchName(m.getBatchName());
			mentorBatchResDto.setBatchStrength(m.getEmployee().stream().count());
			mentorBatchResDto.setStartDate(m.getStartDate());
			mentorBatchResDto.setEndDate(m.getEndDate());
			mentorBatchResDto.setStatus(m.getStatus());
			List<String> list = new ArrayList<String>();
			m.getTechId().stream().forEach(t -> {
				list.add(t.getTech());
			});
			mentorBatchResDto.setTechnologies(list);
			arrayList.add(mentorBatchResDto);
		});
		return arrayList;
	}

	@Override
	public void giveAttendance(AttendanceDto attendance) {
		Attendance attendanceDetails = new Attendance();
		BeanUtils.copyProperties(attendance, attendanceDetails);
		Employee employee = employeeDao.findByEmpId(attendance.getEId());
		List<Attendance> attendances = employee.getAttendances();
		attendances.add(attendanceDetails);
		employeeDao.save(employee);
		attendanceDetails.setEmployee(employee);
		attendaceDao.save(attendanceDetails);
	}

	@Override
	public MockRatings giveMockRatings(AddMockRatingsDto ratings) {
		MockRatings mockRatings = new MockRatings();
		Employee employee = employeeDao.findByEmpId(ratings.getEmpid());
		Technologies technologies = technologyDao.findById(ratings.getTechId()).get();
		BeanUtils.copyProperties(ratings, mockRatings);
		mockRatings.setTech(technologies);
		mockRatings.setEmployee(employee);
		ratingDao.save(mockRatings);
		
		List<MockRatings> mockDetails = employee.getMockDetails();
		mockDetails.add(mockRatings);
		employeeDao.save(employee);
		
		return mockRatings;
	}

	@Override
	public String changePassword(ChangePasswordDto dto) {
		UserInfo userDetails = infoRepo.findByUsername(dto.getEmpId());
		if (userDetails == null) {
			throw new RuntimeException("No user with the Username");
		}
		if (dto.getExistingPassword().equals(userDetails.getPassword())
				&& dto.getNewPassword().equals(dto.getReTypeNewPassword())) {
			userDetails.setPassword(dto.getNewPassword());
			infoRepo.save(userDetails);
			
			Employee empId = employeeDao.findByEmpId(dto.getEmpId());
			if(empId!=null) {
			empId.setPassword(dto.getNewPassword());
			employeeDao.save(empId);
			}else {
			Mentor findByEmpId = mentorDao.findByEmpId(dto.getEmpId());
			findByEmpId.setPassword(dto.getNewPassword());
			mentorDao.save(findByEmpId);
			}
			return dto.getNewPassword();
		}else {
			return "Password Did not update";
		}
	}

}
