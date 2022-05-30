package com.te.lmsproject.entity.employee;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.te.lmsproject.enums.AddressType;
import com.te.lmsproject.enums.State;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address_info")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer employeeAddressId;
	
	@Enumerated(EnumType.STRING)
	private AddressType employeeAddressType;
	
	private String doorNum;
	private String street;
	private String locality;
	private String city;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	private Integer pincode;
	private String landmark;

}
