package com.kims.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandler {

	@GetMapping("/error")
	public ResponseEntity<Object> universalError() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "f");
		map.put("data", "no party found with this Id");
		map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
