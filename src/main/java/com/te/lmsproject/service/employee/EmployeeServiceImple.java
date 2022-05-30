package com.te.lmsproject.service.employee;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.dao.UserInfoRepo;
import com.te.lmsproject.dao.admin.MentorDao;
import com.te.lmsproject.dao.admin.RequestDao;
import com.te.lmsproject.dao.employee.EmployeeDao;
import com.te.lmsproject.dto.ChangePasswordDto;
import com.te.lmsproject.dto.employee.AddEmployeeDto;
import com.te.lmsproject.entity.admin.Mentor;
import com.te.lmsproject.entity.admin.Request;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.entity.mentor.MockRatings;
import com.te.lmsproject.entity.util.UserInfo;

@Service
public class EmployeeServiceImple implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private RequestDao requestDao;

	@Autowired
	private UserInfoRepo infoRepo;
	
	@Autowired
	private MentorDao mentorDao;


	@Override
	@Transactional
	public Employee saveEmp(AddEmployeeDto employee){
		if(employeeDao.findByEmployeeId(employee.getEmployeeId())==null){
		Employee emp = new Employee();
		BeanUtils.copyProperties(employee, emp);
		emp.setStatus("Active");
		emp.setAddressInfo(employee.getAddressInfo());
		Employee save = employeeDao.save(emp);
		Request request = new Request();
		request.setEmployeeId(emp.getEmployeeId());
		requestDao.save(request);
		return save;
		}else {
			throw new DuplicateDataException("duplicate entry");
		}

	}

	@Override
	public Employee updateEmp(AddEmployeeDto addEmployee) {
		Employee employee = employeeDao.findByEmployeeId(addEmployee.getEmployeeId());
		if(employee==null) {
			throw new DataViolationException("Employee doess not exist");
		}
		BeanUtils.copyProperties(addEmployee, employee);
		employeeDao.save(employee);
		return employee;
	}

	@Override
	public Employee getEmpDetails(String empName){
		Employee findByEmpName = employeeDao.findByEmployeeName(empName);
		if(findByEmpName==null) {
			throw new DataViolationException("Employee does not exist");
		}
		
		return findByEmpName;
	}

	

	@Override
	public List<Employee> getAllEmp() {
		List<Employee> findAll = employeeDao.findAll();
		if(findAll.isEmpty()) {
			throw new DataViolationException("Employee Details are unavaliable");
		}
		return findAll;
	}

	@Override
	public List<MockRatings> getMockDetails(String empName){
		Employee findByEmpName = employeeDao.findByEmployeeName(empName);
		if(findByEmpName==null) {
			throw new DataViolationException("Employee does not exist");
		}
		List<MockRatings> mockDetails = findByEmpName.getMockDetails();
		if(mockDetails.isEmpty()) {
			throw new DataViolationException("Employee does not contain any mock details");
		}
		
		return mockDetails;
		
	}

	@Override
	public String changePassword(ChangePasswordDto dto) {
		UserInfo userDetails = infoRepo.findByUsername(dto.getEmployeeId());
		if (userDetails == null) {
			throw new DataViolationException("No user with the Username");
		}
		if (dto.getExistingPassword().equals(userDetails.getPassword())
				&& dto.getNewPassword().equals(dto.getReTypeNewPassword())) {
			userDetails.setPassword(dto.getNewPassword());
			infoRepo.save(userDetails);
			
			Employee empId = employeeDao.findByEmployeeId(dto.getEmployeeId());
			if(empId!=null) {
			empId.setPassword(dto.getNewPassword());
			employeeDao.save(empId);
			}else {
			Mentor findByEmpId = mentorDao.findByEmployeeId(dto.getEmployeeId());
			findByEmpId.setPassword(dto.getNewPassword());
			mentorDao.save(findByEmpId);
			}
			return dto.getNewPassword();
		}else {
			return "Password Did not update";
		}
	}

}
