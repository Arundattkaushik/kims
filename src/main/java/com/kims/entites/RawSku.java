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
	private String quantity;
	private String price_per_unit;
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
		this.name = name.trim();
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity.trim();
	}
	public String getPrice_per_unit() {
		return price_per_unit;
	}
	public void setPrice_per_unit(String price_per_unit) {
		this.price_per_unit = price_per_unit.trim();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description.trim();
	}
	@Override
	public String toString() {
		return "RawSku [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price_per_unit=" + price_per_unit
				+ ", description=" + description + "]";
	}	
}
