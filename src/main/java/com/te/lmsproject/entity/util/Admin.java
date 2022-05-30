package com.te.lmsproject.entity.util;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.lmsproject.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_info")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	private String adminId;

	private String adminName;
	
	private String emailId;
	@JsonIgnore
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String role;
	
	

}
