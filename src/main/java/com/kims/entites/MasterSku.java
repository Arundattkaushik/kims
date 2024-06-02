package com.kims.entites;

import java.util.Arrays;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MasterSku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int masterSkuQty;
	private String masterSkuTitle;
	private String masterSkuDescription;
	
	@ElementCollection
	private String[] rawSku;
	
	@ElementCollection
	private String[] rawskuquantity;
	private String stockValue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMasterSkuQty() {
		return masterSkuQty;
	}
	public void setMasterSkuQty(int masterSkuQty) {
		this.masterSkuQty = masterSkuQty;
	}
	public String getMasterSkuTitle() {
		return masterSkuTitle;
	}
	public void setMasterSkuTitle(String masterSkuTitle) {
		this.masterSkuTitle = masterSkuTitle.trim();
	}
	public String getMasterSkuDescription() {
		return masterSkuDescription;
	}
	public void setMasterSkuDescription(String masterSkuDescription) {
		this.masterSkuDescription = masterSkuDescription.trim();
	}
	public String[] getRawSku() {
		return rawSku;
	}
	public void setRawSku(String[] rawSku) {
		this.rawSku = rawSku;
	}
	public String[] getRawskuquantity() {
		return rawskuquantity;
	}
	public void setRawskuquantity(String[] rawskuquantity) {
		this.rawskuquantity = rawskuquantity;
	}
	public String getStockValue() {
		return stockValue;
	}
	public void setStockValue(String stockValue) {
		this.stockValue = stockValue;
	}
	@Override
	public String toString() {
		return "MasterSku [id=" + id + ", masterSkuQty=" + masterSkuQty + ", masterSkuTitle=" + masterSkuTitle
				+ ", masterSkuDescription=" + masterSkuDescription + ", rawSku=" + Arrays.toString(rawSku)
				+ ", rawskuquantity=" + Arrays.toString(rawskuquantity) + ", stockValue=" + stockValue + "]";
	}
	
}
