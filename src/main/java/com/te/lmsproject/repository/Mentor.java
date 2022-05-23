package com.te.lmsproject.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "mentor_info")
public class Mentor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String mentorName;
	private String employeeId;
	private String emailId;
	private String password;
	
	@ManyToMany(targetEntity = Technologies.class)
	private List<Technologies> skills;
	
	@OneToMany(mappedBy = "mentor")
	@JsonBackReference
	private List<Batch> batch;

}
