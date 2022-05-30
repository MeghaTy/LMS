package com.te.lmsproject.enums;

public enum Gender {
	MALE("MALE"),FEMALE("FEMALE"),OTHERS("OTHERS");
	private final String temp;
	
	Gender(String gender){
		this.temp=gender;
	}

	public String getTemp() {
		return temp;
	}

}
