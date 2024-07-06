package com.kims.entites;

import java.time.LocalDate;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SalesInvoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int invoiceNumber;
	
	/* Company GSTIN & Contact Details */
	private String companyGstin; 
	private String companyName; 
	private String companyAddress; 
	private String companyMobile; 
	private String additionalContactNo; 
	
	
	
	/* Bill to details */
	private String billToName;
	private String select_billTo_party;
	private String billToAddress;
	private String billToState;
	private String billToStateCode;
	private String billToGstInNo;
	
	
	/* Ship to details */
	private String shippToName;
	private String select_shipTo_party;
	private String shippToAddress;
	private String shippToState;
	private String shippToStateCode;
	private String shippToGstInNo;
	
	/* General Details */
	private LocalDate invDate;
	
	/* Ordered Product Details */
	@ElementCollection
	private String[] hsn;
	
	@ElementCollection
	private String[] masterSku;
	
	@ElementCollection
	private String[] particulars;
	
	@ElementCollection
	private int[] qty;
	
	@ElementCollection
	private float[] price;
	
	@ElementCollection
	private float[] amount;

	/* Bank Details */
	private String bankName;
	private String bankAccountNo;
	private String bankIFSC;
	
	/* Total & (GST+Courier) Details */
	private float fTotal;
	private float fcgst;
	private float fcgstAmt;
	private float fsgst;
	private float fsgstAmt;
	private float figst;
	private float figstAmt;
	private float courierCharges;
	private float fgTotal;
	
	/* Terms And Conditions */
	private String termsAndCondtions;
	
	
}
