package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kims.entites.SalesInvoice;

public interface SalesInvoiceRepo extends JpaRepository<SalesInvoice, Long> {

	@Query("select s from SalesInvoice s ORDER BY s.id desc")
	public List<SalesInvoice> getSalesData();
}
