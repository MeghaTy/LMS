package com.te.lmsproject.enums;

public enum BatchStatus {
		IN_PROGRESS("IN_PROGRESS"),YET_TO_START("TO_BE_START"),COMPLETED("COMPLETED");
	
	private final String status;
	
	BatchStatus(String status){
		this.status=status;
	}

}
