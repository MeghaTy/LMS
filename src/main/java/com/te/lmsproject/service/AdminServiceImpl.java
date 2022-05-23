package com.te.lmsproject.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dao.BatchDao;
import com.te.lmsproject.dao.EmployeeDao;
import com.te.lmsproject.dao.MentorDao;
import com.te.lmsproject.dao.RequestDao;
import com.te.lmsproject.dao.TechnologyDao;
import com.te.lmsproject.dao.UserInfoRepo;
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
import com.te.lmsproject.repository.Technologies;
import com.te.lmsproject.repository.UserInfo;
import com.te.lmsproject.util.EmailServices;

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

	String technology ="Technology not found";
	String batchMsg = "Batch not found";
	String mentorMsg ="Mentor not found";
	@Override
	public Batch addBatch(AddBatchDto batchDto) {
		Batch batchId = batchDao.findByBatchId(batchDto.getBatchId());
		if (batchId != null) {
			throw new DuplicateDataException("Duplicate entry");
		}
		Mentor mentor = mentorDao.findByMentorName(batchDto.getMentorName());
		if (mentor == null) {
			throw new DuplicateDataException(mentorMsg);
		}
		List<Technologies> findAllById = technologyDao.findAllById(batchDto.getTechnicalId());
		if (findAllById.isEmpty()) {
			throw new DataViolationException(technology);
		}
		Batch batch = new Batch();
		batch.setMentor(mentor);
		batch.setTechnicalId(findAllById);
		BeanUtils.copyProperties(batchDto, batch);
		batchDao.save(batch);
		return batch;
	}

	@Override
	public Batch updateBatch(UpdateBatchDto batch){
		Batch batchId = batchDao.findByBatchId(batch.getBatchId());
		if (batchId == null) {
			throw new DataViolationException(batchMsg);
		}
		if (batchDao.findByBatchName(batch.getBatchName()) != null) {
			throw new DuplicateDataException("Batch name already exist");
		}

		List<Technologies> findAllById = technologyDao.findAllById(batch.getTechnicalId());
		if (findAllById.isEmpty()) {
			throw new DataViolationException(technology);
		}
		BeanUtils.copyProperties(batch, batchId);
		batchId.setTechnicalId(findAllById);
		batchDao.save(batchId);
		return batchId;

	}

	@Override
	public void deleteBatch(Integer batch) {
		Batch batchId = batchDao.findByBatchId(batch);
		if (batchId == null) {
			throw new DataViolationException(batchMsg);
		}
		batchDao.delete(batchId);

	}

	@Override
	public Mentor addMentor(AddMentorDto mentor){
		Mentor mentorId = mentorDao.findByEmployeeId(mentor.getEmployeeId());
		Mentor mentor2 = new Mentor();
		if (mentorId == null) {
			List<Technologies> findAllById = technologyDao.findAllById(mentor.getSkills());
			if (findAllById.isEmpty()) {
				throw new DataViolationException(technology);
			}
			mentor2.setSkills(findAllById);
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
	public Mentor updateMentor(AddMentorDto mentor){
		Mentor mentor2 = mentorDao.findByEmployeeId(mentor.getEmployeeId());
		if (mentor2 != null) {
			List<Technologies> findAllById = technologyDao.findAllById(mentor.getSkills());
			if (findAllById.isEmpty()) {
				throw new DataViolationException(technology);
			}
			mentor2.setEmailId(mentor.getEmailId());
			mentor2.setMentorName(mentor.getMentorName());
			mentor2.setSkills(findAllById);
			mentor2.setEmployeeId(mentor.getEmployeeId());
			mentorDao.save(mentor2);
			return mentor2;
		} else {
			throw new DataViolationException(mentorMsg);
		}

	}

	@Override
	public Batch getBatch(Integer batchId){
		Batch findByBatchId = batchDao.findByBatchId(batchId);
		if (findByBatchId == null) {
			throw new DataViolationException(batchMsg);
		}
		return findByBatchId;
	}

	@Override
	public List<AdminBatchDispalyDto> getAllBatch(){
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
			throw new DataViolationException(mentorMsg);
		}
		return findByEmpId;
	}

	@Override
	public List<Mentor> geAlltMentor(){
		List<Mentor> findAll = mentorDao.findAll();
		if (findAll.isEmpty()) {
			throw new DuplicateDataException("No mentor details avaliable");
		}
		return findAll;
	}

	@Override
	public void deleteMentor(String empId){
		Mentor findByEmpId = mentorDao.findByEmployeeId(empId);
		if (findByEmpId == null) {
			throw new DataViolationException(mentorMsg);
		}
		mentorDao.delete(findByEmpId);

	}

	@Override
	public List<EmployeeRequestDto> getAllRequest(){
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
			dto.setPercentage(employee.getEducationInfos().get(employee.getEducationInfos().size() - 1).getPercentage());
			dto.setYearOfPassOut(
					employee.getEducationInfos().get(employee.getEducationInfos().size() - 1).getYearOfPassOut());

			dtos.add(dto);

		}
		return dtos;
	}

	@Override
	@Transactional
	public List<Employee> approveRequest(RequestApproveDto approve){
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
		mentor.stream().forEach(m -> 
			list.add(new DropDownDto(m.getId(), m.getMentorName()))
		);
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
