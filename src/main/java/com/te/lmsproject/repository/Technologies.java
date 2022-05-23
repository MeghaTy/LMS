package com.te.lmsproject.repository;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Technologies {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tid")
	private Integer id;
	private String technology;
	
	
}
