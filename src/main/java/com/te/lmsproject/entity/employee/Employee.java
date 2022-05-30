package com.te.lmsproject.entity.employee;

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
import com.te.lmsproject.entity.mentor.Attendance;
import com.te.lmsproject.entity.mentor.MockRatings;
import com.te.lmsproject.enums.BloodGroup;
import com.te.lmsproject.enums.Gender;
import com.te.lmsproject.enums.Nationality;

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

	private String status;

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

	@OneToMany
	@JsonManagedReference
	private List<MockRatings> mockDetails;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Attendance> attendances;

}
