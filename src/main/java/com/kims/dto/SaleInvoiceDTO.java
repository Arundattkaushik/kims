package com.kims.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import com.kims.entites.MasterSku;
import lombok.Data;

@Data
@Service
public class SaleInvoiceDTO {
	
		//Generating invoice number
		private String invoiceNumber;
		
		/* Company GSTIN & Contact Details */
		private String companyGstin; 
		private String hudyamDl; 
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
		private String select_shipTo_party;
		private String shippToAddress;
		private String shippToState;
		private String shippToStateCode;
		private String shippToGstInNo;
		private String eWayBillNo;
		private String pONumber;
		
		/* General Details */
		private LocalDate invDate;
		
		/* Ordered Product Details */		
		private List<MasterSku> masterSku = new ArrayList<MasterSku>();
		
		private List<Integer> qty = new ArrayList<Integer>();
		
		private List<String> uom;
		
		private List<BigDecimal> price;
		
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
