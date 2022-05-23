package com.te.lmsproject.repository;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.lmsproject.enums.YearOfExp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "experience_info")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer employeeExpId;
	
	private String companyName;
	
	@Enumerated(EnumType.ORDINAL)
	private YearOfExp yearsOfExp;
	
	private LocalDate dateOfJoining;
	private LocalDate dateOfRelieving;
	private String designation;
	private String location;


}
