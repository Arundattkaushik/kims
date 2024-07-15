package com.kims.dto;

import com.kims.entites.Party;
import com.kims.entites.RawSku;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class RawPartyDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;
	
	@ManyToOne
	@JoinColumn(name = "rsku_id")
	private RawSku rawSku; 
	
}
