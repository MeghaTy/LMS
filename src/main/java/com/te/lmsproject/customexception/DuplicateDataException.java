package com.te.lmsproject.customexception;

@SuppressWarnings("serial")
public class DuplicateDataException extends RuntimeException{

	public DuplicateDataException(String message) {
		super(message);
	}
}
