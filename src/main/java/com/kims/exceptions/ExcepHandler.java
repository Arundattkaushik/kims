package com.kims.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepHandler {

	@ExceptionHandler(value = Exception.class)
	public String loggedOutException() {
		return "/error";
	}
	
	@ExceptionHandler(value = NullPointerException.class )
	public String nullPointerException() {
		return "/error";
	}
}
