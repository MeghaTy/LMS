package com.te.lmsproject.dto.employee;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.te.lmsproject.enums.BloodGroup;
import com.te.lmsproject.enums.EmplyoeeStatus;
import com.te.lmsproject.enums.Gender;
import com.te.lmsproject.enums.Nationality;
import com.te.lmsproject.repository.employee.Address;
import com.te.lmsproject.repository.employee.BankDetails;
import com.te.lmsproject.repository.employee.Contact;
import com.te.lmsproject.repository.employee.EducationalInfo;
import com.te.lmsproject.repository.employee.Experience;
import com.te.lmsproject.repository.employee.SecondaryInfo;
import com.te.lmsproject.repository.employee.TechnicalSkill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEmployeeDto {
	
	@NotEmpty(message =  "Employee Id cannot not be empty")
	@NotNull(message = "Employee is missing")
	private String employeeId;
	@NotEmpty(message =  "Employee name cannot not be empty")
	@NotNull(message = "Employee name is missing")
	private String employeeName;
	@NotEmpty(message =  "Date of joining cannot not be empty")
	@NotNull(message = "Date of joining is missing")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate employeeDateOfJoining;
	@JsonFormat(pattern ="dd/MM/yyyy")
	@NotEmpty(message =  "Date of Birth cannot not be empty")
	@NotNull(message = "Date of birtht is missing")
	private LocalDate employeeDateOfBirth;
	@Email
	private String emailId;
	@NotEmpty(message =  "Blood Group cannot not be empty")
	@NotNull(message = "blood group is missing")
	private BloodGroup bloodGroup;
	@NotEmpty(message =  "Designation cannot not be empty")
	@NotNull(message = "Designation is missing")
	private String designation;
	@NotEmpty(message ="Nationality cannot not be empty")
	@NotNull(message = "Nationality is missing")
	@Enumerated(EnumType.STRING)
	private Nationality nationality;
	@NotEmpty(message =  "Gender cannot not be empty")
	@NotNull(message = "Gender is missing")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@NotEmpty(message =  "Employee status cannot not be empty")
	@NotNull(message = "Employee status is missing")
	@Enumerated(EnumType.STRING)
	private EmplyoeeStatus status;

	private SecondaryInfo secondaryInfo;

	private List<EducationalInfo> educationInfos ;

	private List<Address> addressInfo;

	private List<TechnicalSkill> technicalSkillInfo;

	private BankDetails bankDetails;

	private List<Experience> experiences;

	private List<Contact> contacts;

}
