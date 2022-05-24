package com.te.lmsproject.repository.employee;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.te.lmsproject.enums.BloodGroup;
import com.te.lmsproject.enums.EmplyoeeStatus;
import com.te.lmsproject.enums.Gender;
import com.te.lmsproject.enums.Nationality;
import com.te.lmsproject.repository.mentor.Attendance;
import com.te.lmsproject.repository.mentor.MockRatings;

import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String employeeId;

	private String employeeName;

	private LocalDate employeeDateOfJoining;

	private LocalDate employeeDateOfBirth;

	private String emailId;
	@JsonIgnore
	private String password;

	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;

	private String designation;

	@Enumerated(EnumType.STRING)
	private Nationality nationality;

	@Enumerated(EnumType.STRING)
	private EmplyoeeStatus status;


	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToOne(cascade = CascadeType.ALL)
	private SecondaryInfo secondaryInfo;

	@OneToMany(cascade = CascadeType.ALL)
	private List<EducationalInfo> educationInfos;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addressInfo;

	@OneToMany(cascade = CascadeType.ALL)
	private List<TechnicalSkill> technicalSkillInfo;

	@OneToOne(cascade = CascadeType.ALL)
	private BankDetails bankDetails;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Experience> experiences;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Contact> contacts;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<MockRatings> mockDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Attendance> attendances;

}
