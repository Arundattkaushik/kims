package com.kims.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawSku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String party_list;
	private int quantity;
	private int price_per_unit;
	private String description;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getPrice_per_unit() {
		return price_per_unit;
	}



	public void setPrice_per_unit(int price_per_unit) {
		this.price_per_unit = price_per_unit;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getParty_list() {
		return party_list;
	}



	public void setParty_list(String party_list) {
		this.party_list = party_list;
	}



	@Override
	public String toString() {
		return "RawSku [id=" + id + ", name=" + name + ", party_list=" + party_list + ", quantity=" + quantity
				+ ", price_per_unit=" + price_per_unit + ", description=" + description + "]";
	}
	
}
