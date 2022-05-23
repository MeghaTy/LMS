package com.te.lmsproject.customexception;

public class DuplicateDataException extends RuntimeException{

	public DuplicateDataException(String message) {
		super(message);
	}
}
