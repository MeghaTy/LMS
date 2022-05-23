package com.te.lmsproject.repository;

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
	private Long empAccountNum;
	
	private String empBankName;
	
	private String empAccountType;
	
	private String empIFSCCode;
	
	private String empBranch;
	
	@Enumerated(EnumType.STRING)
	private State empState;
	

}
