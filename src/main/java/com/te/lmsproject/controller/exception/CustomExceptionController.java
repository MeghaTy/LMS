package com.te.lmsproject.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.lmsproject.customexception.DataViolationException;
import com.te.lmsproject.customexception.DuplicateDataException;
import com.te.lmsproject.entity.util.ResponseBody;

@ControllerAdvice
public class CustomExceptionController {

	@ExceptionHandler(value = DuplicateDataException.class)
	public ResponseEntity<ResponseBody> duplicateDataException(DuplicateDataException exception) {
		
		
		return new ResponseEntity<>(new ResponseBody(true,exception.getMessage(),null), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(value = DataViolationException.class)
	public ResponseEntity<ResponseBody> dataViolation(DataViolationException exception) {
		
		
		return new ResponseEntity<>(new ResponseBody(true,exception.getMessage(),null), HttpStatus.NOT_FOUND);
	}
}
