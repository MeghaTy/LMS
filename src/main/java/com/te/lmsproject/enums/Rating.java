package com.te.lmsproject.enums;

public enum Rating {
	BELOW_AVERAGE("BELOW_AVERAGE"),AVERAGE("AVERAGE"),ABOVE_AVERAGE("ABOVE_AVERAGE"),GOOD("GOOD"),EXCELLENT("EXCELLENT");
	
	private final String ratings;
	

	Rating(String ratings){
		this.ratings=ratings;
	}
}


