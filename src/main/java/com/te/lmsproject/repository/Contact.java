package com.te.lmsproject.repository;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "contact_info")
public class Contact {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer employeeContactId;
	
	private String contactType;
	private String contactNumber;
	

}
