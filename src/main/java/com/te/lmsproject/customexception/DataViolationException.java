package com.te.lmsproject.customexception;

public class DataViolationException extends RuntimeException {
	public DataViolationException(String msg) {
		super(msg);
	}
}
