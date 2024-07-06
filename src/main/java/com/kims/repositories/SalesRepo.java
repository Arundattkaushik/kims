package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.kims.entites.SalesInvoice;

public interface SalesRepo extends JpaRepository<SalesInvoice, Integer> {
	
	@Query("select s from SalesInvoice s ORDER BY s.invoiceNumber desc")
	public List<SalesInvoice> getSalesData();
}
