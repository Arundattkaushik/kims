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
public class RawSku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	private Long partyId;
	
	private int quantity;
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date doc;
	
	@PrePersist
    protected void onCreate() {
        this.doc = new Date();
    }
	
}
