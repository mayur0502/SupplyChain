package com.jbk.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		
//		ex.getBindingResult().getFieldErrors().forEach(error -> {
//			map.put(error.getField(), error.getDefaultMessage());
//		});
		
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		
		for (FieldError fieldError : fieldErrors) {
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return map;
	}
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<String> resourceAlreadyExistException(ResourceAlreadyExistException ex){
		return ResponseEntity.ok(ex.getMessage());
	}

}
