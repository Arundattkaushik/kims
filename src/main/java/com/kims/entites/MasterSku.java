package com.kims.entites;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
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
	private String hsn;
	
	public MasterSku(String masterSkuDescription, String hsn) {
		super();
		this.masterSkuDescription = masterSkuDescription;
		this.hsn = hsn;
	}

	public MasterSku() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	

}
