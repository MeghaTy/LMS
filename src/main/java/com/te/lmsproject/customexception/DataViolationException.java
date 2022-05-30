package com.te.lmsproject.customexception;

@SuppressWarnings("serial")
public class DataViolationException extends RuntimeException {
	public DataViolationException(String msg) {
		super(msg);
	}
}
