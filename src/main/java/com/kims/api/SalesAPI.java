package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.SalesInvoice;
import com.kims.services.SalesService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.metamodel.mapping.ForeignKeyDescriptor.Side;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class SalesAPI {
	@Autowired
	private SalesService salesService;

	@GetMapping("/get-sales")
	public ResponseEntity<Object> getSalesData() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		map.put("data", salesService.getSalesInvoice());
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
		
	}
	
}
