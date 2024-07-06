package com.kims.entites;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class SoldRawSku {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rSku;
	private int quantity;
	private LocalDate date;	
	
}
