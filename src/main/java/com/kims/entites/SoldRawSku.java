package com.kims.entites;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class SoldRawSku {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long rId;
	private String name;
	private int soldQty;
	
	@Temporal(TemporalType.DATE)
	private Date date;	
	
	@PrePersist
    protected void onCreate() {
        this.date = new Date();
    }
	
}
