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
	private String masterSkuTitle;
	private String masterSkuDescription;
	
	@ElementCollection
	private String[] rawSku;
	
	@ElementCollection
	private int[] rawskuquantity;
	private int masterSkuPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMasterSkuTitle() {
		return masterSkuTitle;
	}
	public void setMasterSkuTitle(String masterSkuTitle) {
		this.masterSkuTitle = masterSkuTitle.trim().toLowerCase();
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
	public int[] getRawskuquantity() {
		return rawskuquantity;
	}
	public void setRawskuquantity(int[] rawskuquantity) {
		this.rawskuquantity = rawskuquantity;
	}
	public int getMasterSkuPrice() {
		return masterSkuPrice;
	}
	public void setMasterSkuPrice(int masterSkuPrice) {
		this.masterSkuPrice = masterSkuPrice;
	}
	@Override
	public String toString() {
		return "MasterSku [id=" + id + ", masterSkuTitle=" + masterSkuTitle + ", masterSkuDescription="
				+ masterSkuDescription + ", rawSku=" + Arrays.toString(rawSku) + ", rawskuquantity="
				+ Arrays.toString(rawskuquantity) + ", masterSkuPrice=" + masterSkuPrice + "]";
	}
		
}
