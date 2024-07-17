package com.kims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.SalesInvoice;
import com.kims.repositories.SalesInvoiceRepo;

@Service
public class SalesService {
	@Autowired
	private SalesInvoiceRepo sInvoiceRepos;
	
	public Optional<SalesInvoice> save(SalesInvoice salesInvoice) {
		return Optional.ofNullable(sInvoiceRepos.save(salesInvoice));	
	}
	
	public List<SalesInvoice> getSalesInvoice() {		
		return sInvoiceRepos.getSalesData();
	}
	
	
	public SalesInvoice findSaleInvoiceById(Long id){
		return sInvoiceRepos.invoiceById(id);
	}

}
