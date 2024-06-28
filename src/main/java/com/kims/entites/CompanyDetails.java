package com.kims.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CompanyDetails {

	@Id
	private int id;
	private String companyName;
	private String companyOwner;
	private String companyEmail;
	private String companyContactNo;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyOwner() {
		return companyOwner;
	}
	public void setCompanyOwner(String companyOwner) {
		this.companyOwner = companyOwner;
	}
	public String getCompanyEmail() {
		return companyEmail;
	}
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}
	public String getCompanyContactNo() {
		return companyContactNo;
	}
	public void setCompanyContactNo(String companyContactNo) {
		this.companyContactNo = companyContactNo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getCompanyMailingAddress() {
		return companyMailingAddress;
	}
	public void setCompanyMailingAddress(String companyMailingAddress) {
		this.companyMailingAddress = companyMailingAddress;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankIfscCode() {
		return bankIfscCode;
	}
	public void setBankIfscCode(String bankIfscCode) {
		this.bankIfscCode = bankIfscCode;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}
	
	
	@Override
	public String toString() {
		return "CompanyDetails [id=" + id + ", companyName=" + companyName + ", companyOwner=" + companyOwner
				+ ", companyEmail=" + companyEmail + ", companyContactNo=" + companyContactNo + ", country=" + country
				+ ", state=" + state + ", city=" + city + ", zipCode=" + zipCode + ", gstin=" + gstin
				+ ", companyMailingAddress=" + companyMailingAddress + ", bankName=" + bankName + ", bankAccountNumber="
				+ bankAccountNumber + ", bankIfscCode=" + bankIfscCode + ", accountType=" + accountType
				+ ", termsAndConditions=" + termsAndConditions + "]";
	}	
	
}
