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

/*
 * doc-> date of creation
 */
@Data
@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String mobile;
	private String email;
	private String address;
	private String state;
	private int stateCode;
	private String gstin;
	
	@Temporal(TemporalType.DATE)
	private Date doc;

	
	@PrePersist
    protected void onCreate() {
        this.doc = new Date();
    }
	
}
