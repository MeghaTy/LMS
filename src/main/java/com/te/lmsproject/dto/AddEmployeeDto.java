package com.te.lmsproject.dto;

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
import com.te.lmsproject.repository.Address;
import com.te.lmsproject.repository.BankDetails;
import com.te.lmsproject.repository.Contact;
import com.te.lmsproject.repository.EducationalInfo;
import com.te.lmsproject.repository.Experience;
import com.te.lmsproject.repository.SecondaryInfo;
import com.te.lmsproject.repository.TechnicalSkill;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEmployeeDto {
	
	@NotEmpty(message =  "Employee Id cannot not be empty")
	@NotNull(message = "Employee is missing")
	private String empId;
	@NotNull
	@NotEmpty(message =  "Employee name cannot not be empty")
	@NotNull(message = "Employee name is missing")
	private String empName;
	@NotEmpty(message =  "Date of joining cannot not be empty")
	@NotNull(message = "Date of joining is missing")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate empDoj;
	@JsonFormat(pattern ="dd/MM/yyyy")
	@NotEmpty(message =  "Date of Birth cannot not be empty")
	@NotNull(message = "Date of birtht is missing")
	private LocalDate empDob;
	@Email
	private String emailId;
	@NotEmpty(message =  "Blood Group cannot not be empty")
	@NotNull(message = "blood group is missing")
	private BloodGroup empBloodGroup;
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
	private EmplyoeeStatus empStatus;

	private SecondaryInfo secondaryInfo;

	private List<EducationalInfo> eduInfos ;

	private List<Address> addressInfo;

	private List<TechnicalSkill> techInfo;

	private BankDetails bankDetails;

	private List<Experience> experiences;

	private List<Contact> contacts;

}