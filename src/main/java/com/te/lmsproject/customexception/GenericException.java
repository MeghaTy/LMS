package com.te.lmsproject.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.lmsproject.repository.ResponseBody;

@ControllerAdvice
public class GenericException {

	@ExceptionHandler(value = DuplicateDataException.class)
	public ResponseEntity<ResponseBody> duplicateDataException(DuplicateDataException exception) {
		
		
		return new ResponseEntity<>(new ResponseBody(true,exception.getMessage(),null), HttpStatus.NOT_FOUND);
	}// end of addCategoryException()
	
	
	@ExceptionHandler(value = DataViolationException.class)
	public ResponseEntity<ResponseBody> DataViolationException(DataViolationException exception) {
		
		
		return new ResponseEntity<>(new ResponseBody(true,exception.getMessage(),null), HttpStatus.NOT_FOUND);
	}
}
