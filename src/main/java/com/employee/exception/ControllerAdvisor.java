package com.employee.exception;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor  {

	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		return new ResponseEntity<>("Employee with entered EmployeeID not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<Object> handleNoDataFound(NoDataFoundException ex) {
		return new ResponseEntity<>("No Data Found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleInvalidArgument(MethodArgumentNotValidException ex) {

		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=ValidationException.class)
	public ResponseEntity<Object> handleValidationException(ValidationException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}

	//@org.springframework.web.bind.annotation.ExceptionHandler
	@ExceptionHandler(value = InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	Map<String,String> digitsOnlyException(Exception e){

		Map<String,String> response = new HashMap<>();
		response.put("status","Type Only Digits");
		response.put("Error","404");
		return response;
	}

	@ExceptionHandler(value = JsonParseException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	Map<String,String> customJsonParseException(Exception e){

		Map<String,String> response = new HashMap<>();
		response.put("status","Unknown JSON Format");
		response.put("Error","404");
		return response;
	}


}
