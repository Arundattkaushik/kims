package com.kims.entites;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.kims.utilities.Utils;

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
	private Long id;
	
	//Generating invoice number
	private String invoiceNumber = Utils.generateInvoiceNumber();
	
	/* Company GSTIN & Contact Details */
	private String companyGstin; 
	private String heWayNo; 
	private String companyName; 
	private String companyAddress; 
	private String companyMobile; 
	private String additionalContactNo; 
	
	
	
	/* Bill to details */
	private String billToName;
	
	@Transient
	private String select_billTo_party;
	private String billToAddress;
	private String billToState;
	private String billToStateCode;
	private String billToGstInNo;
	
	
	/* Ship to details */
	private String shippToName;
	
	@Transient
	private String select_shipTo_party;
	private String shippToAddress;
	private String shippToState;
	private String shippToStateCode;
	private String shippToGstInNo;
	
	/* General Details */
	private LocalDate invDate;
	
	/* Ordered Product Details */
	@ElementCollection
	private List<String> hsn;
	
	@ElementCollection
	private List<Long> masterSku = new ArrayList<Long>();
	
	@ElementCollection
	private List<Integer> qty = new ArrayList<Integer>();
	
	@ElementCollection
	private List<BigDecimal> weight;
	
	@ElementCollection
	private List<BigDecimal> price;
	
	@ElementCollection
	private List<BigDecimal> amount;

	/* Bank Details */
	private String bankName;
	private String bankAccountNo;
	private String bankIFSC;
	
	/* Total & (GST+Courier) Details */
	private BigDecimal fTotal;
	private BigDecimal fcgst;
	private BigDecimal fcgstAmt;
	private BigDecimal fsgst;
	private BigDecimal fsgstAmt;
	private BigDecimal figst;
	private BigDecimal figstAmt;
	private BigDecimal courierCharges;
	private BigDecimal fgTotal;
	private String gTotalInWords;
	
	/* Terms And Conditions */
	private String termsAndCondtions;
	
	
}
