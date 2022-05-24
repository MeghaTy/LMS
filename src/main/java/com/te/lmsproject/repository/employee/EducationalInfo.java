package com.te.lmsproject.repository.employee;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.lmsproject.enums.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer employeeEduId;
	
	private String educationType;
	
	private Integer yearOfPassOut;
	
	private Double percentage;
	
	private String university;
	
	private String instituteName;
	
	private String specialization;
	
	@Enumerated(EnumType.STRING)
	private State state;
}
