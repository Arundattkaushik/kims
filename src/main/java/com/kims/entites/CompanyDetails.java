package com.kims.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CompanyDetails {

	@Id
	private int id;
	private String companyName;
	private String companyOwner;
	private String companyEmail;
	private String companyContactNo;
	private String additionalContactNo;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String gstin;
	private String companyMailingAddress;
	private String bankName;
	private String bankAccountNumber;
	private String bankIfscCode;
	private String accountType;
	private String termsAndConditions;
	
}
