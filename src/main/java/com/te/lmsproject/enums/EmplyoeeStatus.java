package com.te.lmsproject.enums;

public enum EmplyoeeStatus {
	ABSCONDED("ABSCONDED"),ACTIVE("ACTIVE"),TERMINATED("TERMINATED");
	
	private final String status;
	
	EmplyoeeStatus(String status){
		this.status=status;
	}

}
