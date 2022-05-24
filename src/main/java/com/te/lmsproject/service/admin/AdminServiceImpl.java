package com.te.lmsproject.service.admin;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dao.admin.BatchDao;
import com.te.lmsproject.dao.admin.MentorDao;
import com.te.lmsproject.dao.admin.RequestDao;
import com.te.lmsproject.dao.employee.EmployeeDao;
import com.te.lmsproject.dao.util.TechnologyDao;
import com.te.lmsproject.dao.util.UserInfoRepo;
import com.te.lmsproject.dto.admin.AddBatchDto;
import com.te.lmsproject.dto.admin.AddMentorDto;
import com.te.lmsproject.dto.admin.AdminBatchDispalyDto;
import com.te.lmsproject.dto.admin.EmployeeRequestDto;
import com.te.lmsproject.dto.admin.RejectDto;
import com.te.lmsproject.dto.admin.RequestApproveDto;
import com.te.lmsproject.dto.admin.UpdateBatchDto;
import com.te.lmsproject.dto.util.DropDownDto;
import com.te.lmsproject.emailservice.EmailServices;
import com.te.lmsproject.repository.admin.Batch;
import com.te.lmsproject.repository.admin.Mentor;
import com.te.lmsproject.repository.admin.Request;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.util.Technologies;
import com.te.lmsproject.repository.util.UserInfo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private BatchDao batchDao;

	@Autowired
	private MentorDao mentorDao;

	@Autowired
	private TechnologyDao technologyDao;

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private EmailServices emailServices;

	@Autowired
	private UserInfoRepo userInfoRepo;

	private static final String TECHNOLOGY = "Technology not found";
	private static final String BATCH_MESSAGE = "Batch not found";
	private static final String MENTOR_MESSAGE = "Mentor not found";
	private static final String ACTIVE = "ACTIVE";

	@Override
	public Batch addBatch(AddBatchDto batchDto) {
		Batch batchId = batchDao.findByBatchId(batchDto.getBatchId());
		if (batchId != null) {
			throw new DuplicateDataException("Duplicate entry");
		}
		Mentor mentor = mentorDao.findByMentorName(batchDto.getMentorName());
		if (mentor == null) {
			throw new DuplicateDataException(MENTOR_MESSAGE);
		}
		List<Technologies> findAllById = technologyDao.findAllById(batchDto.getTechnicalId());
		if (findAllById.isEmpty()) {
			throw new DataViolationException(TECHNOLOGY);
		}
		Batch batch = new Batch();
		batch.setTechnicalId(findAllById);
		if(mentor.getStatus().equalsIgnoreCase(ACTIVE)) {
			batch.setMentor(mentor);
		}else {
			throw new DataViolationException("Enter a valid mentor ");
		}
		BeanUtils.copyProperties(batchDto, batch);
		batchDao.save(batch);
		return batch;
	}

	@Override
	public Batch updateBatch(UpdateBatchDto batch) {
		Batch batchId = batchDao.findByBatchId(batch.getBatchId());
		if (batchId == null) {
			throw new DataViolationException(BATCH_MESSAGE);
		}
		Mentor findByMentorName = mentorDao.findByMentorName(batch.getMentorName());
		
		List<Technologies> findAllById = technologyDao.findAllById(batch.getTechnicalId());
		if (findAllById.isEmpty()) {
			throw new DataViolationException(TECHNOLOGY);
		}
		BeanUtils.copyProperties(batch, batchId);
		if(findByMentorName.getStatus().equalsIgnoreCase(ACTIVE)) {
			batchId.setMentor(findByMentorName);
		}else {
			throw new DataViolationException("Enter a valid mentor ");
		}
		batchId.setTechnicalId(findAllById);
		batchDao.save(batchId);
		return batchId;

	}

	@Override
	public void deleteBatch(Integer batch) {
		Batch batchId = batchDao.findByBatchId(batch);
		if (batchId == null) {
			throw new DataViolationException(BATCH_MESSAGE);
		}
		batchDao.delete(batchId);

	}

	@Override
	public Mentor addMentor(AddMentorDto mentor) {
		Mentor mentorId = mentorDao.findByEmployeeId(mentor.getEmployeeId());
		Mentor mentor2 = new Mentor();
		if (mentorId == null) {
			List<Technologies> findAllById = technologyDao.findAllById(mentor.getSkills());
			if (findAllById.isEmpty()) {
				throw new DataViolationException(TECHNOLOGY);
			}
			mentor2.setSkills(findAllById);
			mentor2.setStatus(ACTIVE);
			BeanUtils.copyProperties(mentor, mentor2);
			String password = emailServices.sendPassword(mentor2.getEmailId());
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(mentor2.getEmployeeId());
			userInfo.setPassword(password);
			userInfo.setAuthorities("ROLE_MENTOR");
			userInfoRepo.save(userInfo);
			mentorDao.save(mentor2);
			return mentor2;
		} else {
			throw new DuplicateDataException("Duplicate entry");

		}

	}

	@Override
	public Mentor updateMentor(AddMentorDto mentor) {
		Mentor mentor2 = mentorDao.findByEmployeeId(mentor.getEmployeeId());
		if (mentor2 != null) {
			List<Technologies> findAllById = technologyDao.findAllById(mentor.getSkills());
			if (findAllById.isEmpty()) {
				throw new DataViolationException(TECHNOLOGY);
			}
			mentor2.setEmailId(mentor.getEmailId());
			mentor2.setMentorName(mentor.getMentorName());
			mentor2.setSkills(findAllById);
			mentor2.setEmployeeId(mentor.getEmployeeId());
			mentorDao.save(mentor2);
			return mentor2;
		} else {
			throw new DataViolationException(MENTOR_MESSAGE);
		}

	}

	@Override
	public Batch getBatch(Integer batchId) {
		Batch findByBatchId = batchDao.findByBatchId(batchId);
		if (findByBatchId == null) {
			throw new DataViolationException(BATCH_MESSAGE);
		}
		return findByBatchId;
	}

	@Override
	public List<AdminBatchDispalyDto> getAllBatch() {
		List<Batch> batchDetails = batchDao.findAll();
		if (batchDetails.isEmpty()) {
			throw new DataViolationException("No batch detials avaliable");
		}

		List<AdminBatchDispalyDto> list = new ArrayList<>();
		batchDetails.stream().forEach(b -> {
			AdminBatchDispalyDto dto = new AdminBatchDispalyDto();
			BeanUtils.copyProperties(b, dto);
			dto.setMentorName(b.getMentor().getMentorName());
			dto.setTechnologies(b.getTechnicalId());
			list.add(dto);
		});
		return list;
	}

	@Override
	public Mentor getMentor(String empId) {
		Mentor findByEmpId = mentorDao.findByEmployeeId(empId);
		if (findByEmpId == null) {
			throw new DataViolationException(MENTOR_MESSAGE);
		}
		if (findByEmpId.getStatus().equalsIgnoreCase(ACTIVE)) {
			return findByEmpId;
		}else
		{
			
			throw new DataViolationException("Enter valid mentor id");

		}
		
	}

	@Override
	public List<Mentor> geAlltMentor() {
		List<Mentor> findAll = mentorDao.findAll();
		if (findAll.isEmpty()) {
			throw new DuplicateDataException("No mentor details avaliable");
		}
		List<Mentor> list = new ArrayList<>();

		for (Mentor mentor : findAll) {
			if (mentor.getStatus().equalsIgnoreCase(ACTIVE)) {
				list.add(mentor);
			}
		}
		return list;
	}

	@Override
	public void deleteMentor(String empId) {
		Mentor findByEmpId = mentorDao.findByEmployeeId(empId);
		if (findByEmpId == null) {
			throw new DataViolationException(MENTOR_MESSAGE);
		}
		findByEmpId.setStatus("INACTIVE");
		mentorDao.save(findByEmpId);

	}

	@Override
	public List<EmployeeRequestDto> getAllRequest() {
		List<Request> findAll = requestDao.findAll();
		if (findAll.isEmpty()) {
			throw new DataViolationException("Request list is empty");
		}
		List<String> empId = new ArrayList<>();
		for (Request request : findAll) {
			String empId2 = request.getEmployeeId();
			empId.add(empId2);
		}
		List<Employee> findAllById = employeeDao.findByEmployeeIdIn(empId);

		List<EmployeeRequestDto> dtos = new ArrayList<>();
		for (Employee employee : findAllById) {
			EmployeeRequestDto dto = new EmployeeRequestDto();
			dto.setEmployeeId(employee.getEmployeeId());
			dto.setEmployeeName(employee.getEmployeeName());
			dto.setExperience(employee.getExperiences().get(0).getYearsOfExp());
			dto.setContactNumber(employee.getContacts().get(0).getContactNumber());
			dto.setPercentage(
					employee.getEducationInfos().get(employee.getEducationInfos().size() - 1).getPercentage());
			dto.setYearOfPassOut(
					employee.getEducationInfos().get(employee.getEducationInfos().size() - 1).getYearOfPassOut());

			dtos.add(dto);

		}
		return dtos;
	}

	@Override
	@Transactional
	public List<Employee> approveRequest(RequestApproveDto approve) {
		List<Employee> findAll = employeeDao.findByEmployeeIdIn(approve.getEmployeesId());
		if (findAll.isEmpty()) {
			throw new DataViolationException("Employee does not exist");
		}
		Batch batch = batchDao.findByBatchId(approve.getBatchId());
		if (batch == null) {
			throw new DataViolationException("Batch does not exist");
		}
		List<Employee> employee = batch.getEmployee();
		employee.addAll(findAll);
		batch.setEmployee(employee);
		batchDao.save(batch);
		findAll.stream().forEach(e -> {
			String sendPassword = emailServices.sendPassword(e.getEmailId());
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(e.getEmployeeId());
			userInfo.setPassword(sendPassword);
			userInfo.setAuthorities("ROLE_EMPLOYEE");
			userInfoRepo.save(userInfo);
		});

		requestDao.deleteByEmployeeIdIn(approve.getEmployeesId());
		return findAll;
	}

	@Override
	public List<Request> rejectRequest(RejectDto dto) {
		List<Request> findAllById = requestDao.findByEmployeeIdIn(dto.getEmployeeIds());
		for (Request request : findAllById) {
			request.setRejectReason(dto.getReason());
			request.setRejected(true);
			requestDao.save(request);
		}
		return findAllById;
	}

	@Override
	public List<DropDownDto> getMentorName() {
		List<Mentor> mentor = mentorDao.findAll();
		List<DropDownDto> list = new ArrayList<>();
		for (Mentor dropDownDto : mentor) {
			if(dropDownDto.getStatus().equalsIgnoreCase(ACTIVE)) {
			 list.add(new DropDownDto(dropDownDto.getId(), dropDownDto.getMentorName()));
			}
		}
		return list;
	}

	@Override
	public List<DropDownDto> getBatchId() {
		List<Batch> findAll = batchDao.findAll();
		List<DropDownDto> listofdrop = new ArrayList<>();
		for (Batch dropDownDto : findAll) {
			DropDownDto dto = new DropDownDto();
			dto.setId(dropDownDto.getId());
			dto.setName(dropDownDto.getBatchName());
			listofdrop.add(dto);
		}
		return listofdrop;
	}

}
