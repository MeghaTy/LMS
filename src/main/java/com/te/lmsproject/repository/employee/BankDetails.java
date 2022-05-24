package com.te.lmsproject.repository.employee;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.te.lmsproject.enums.State;

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
@Table(name = "bank_info")
public class BankDetails {
	
	@Id
	private Long employeeAccountNum;
	
	private String bankName;
	
	private String accountType;
	
	private String iFSCCode;
	
	private String branch;
	
	@Enumerated(EnumType.STRING)
	private State state;
	

}
