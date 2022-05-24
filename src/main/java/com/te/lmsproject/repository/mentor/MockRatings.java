package com.te.lmsproject.repository.mentor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.te.lmsproject.enums.MockType;
import com.te.lmsproject.repository.employee.Employee;
import com.te.lmsproject.repository.util.Technologies;

import lombok.Data;

@Data
@Entity
public class MockRatings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private MockType mockType;
	
	private String mockTakenBy;
	
	@ManyToOne
	private Technologies tech;
	
	private double practical;
	private double therotical;
	private double overAll;
	
	private String detailedFeedBack;
	
	@ManyToOne
	@JsonBackReference
	private Employee employee;

}