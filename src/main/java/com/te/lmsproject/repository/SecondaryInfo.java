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
		private String empPanNum;
		private String empAdharNum;
		private String empFatherName;
		private String empMotherName;
		private String empSpouseName;
		private String empPassportNum;
		@Enumerated(EnumType.STRING)
		private MaritalStatus empMartialStatus;
		

}
