package com.te.lmsproject.enums;

public enum YearOfExp {
	FRESHER(0), ONEYEAR(1);

	private final Integer type;

	YearOfExp(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}


}
