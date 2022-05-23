package com.te.lmsproject.repository;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.lmsproject.enums.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "emp_secondary_info")
public class SecondaryInfo {
	
		@Id
		private String panNumber;
		private String adharNumber;
		private String fatherName;
		private String motherName;
		private String spouseName;
		private String passportNum;
		@Enumerated(EnumType.STRING)
		private MaritalStatus martialStatus;
		

}
