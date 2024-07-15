package com.kims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kims.entites.PurchaseInvoice;

public interface PurchaseInvoiceRepo extends JpaRepository<PurchaseInvoice, Long> {

}
