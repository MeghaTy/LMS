package com.te.lmsproject.entity.admin;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.te.lmsproject.entity.employee.Employee;
import com.te.lmsproject.entity.mentor.Mock;
import com.te.lmsproject.entity.util.Technologies;
import com.te.lmsproject.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "batch_info")
public class Batch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;
	
	@Column(unique = true)
	private Integer batchId;

	@Column(unique = true)
	private String batchName;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private BatchStatus status;
	
	@OneToMany
	private List<Technologies> technicalId;
	
	@ManyToOne
	@JsonManagedReference
	private Mentor mentor;
	
	@OneToMany
	private List<Employee> employee;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Mock> mock;
	
	
	
	

}
