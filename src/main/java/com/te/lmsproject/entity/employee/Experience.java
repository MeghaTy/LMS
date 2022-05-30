package com.te.lmsproject.entity.employee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfJoining;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfRelieving;
	private String designation;
	private String location;


}
