package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.services.SalesService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/sales")
public class SalesAPI {
	@Autowired
	private SalesService salesService;

	@GetMapping("/list")
	public ResponseEntity<Object> getSalesData() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "s");
		map.put("status", HttpStatus.OK);
		map.put("data", salesService.getSalesInvoice());
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
		
	}
	
}
