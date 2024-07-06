package com.kims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kims.entites.SalesInvoice;
import com.kims.repositories.SalesRepo;

@Service
public class SalesService {
	@Autowired
	private SalesRepo salesRepo;
	
	public SalesInvoice saveSalesInvoice(SalesInvoice salesInvoice) {
		return salesRepo.save(salesInvoice);
	}
	
	public List<SalesInvoice> getSalesInvoice() {		
		return salesRepo.getSalesData();
	}

}
