package com.te.lmsproject.enums;

public enum State {
	KARNATAKA("KARNATAKA"),DELHI("DELHI");
	
	private final String temp;
	
	State(String state){
		this.temp=state;
	}

	public String getTemp() {
		return temp;
	}
	
	

}
