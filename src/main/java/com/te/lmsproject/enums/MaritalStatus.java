package com.te.lmsproject.enums;

public enum MaritalStatus {
	MARRIED("MARRIED"),UNMARRIED("UNMARRIED");
	
	private final String status;
	
	public String getStatus() {
		return status;
	}

	MaritalStatus(String status){
		this.status=status;
	}

}
