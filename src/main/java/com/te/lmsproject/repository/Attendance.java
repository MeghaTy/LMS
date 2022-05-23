package com.te.lmsproject.repository;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	private boolean morning;
	private boolean afternoon;
	@ManyToOne
	@JsonBackReference
	private Employee employee;
}
