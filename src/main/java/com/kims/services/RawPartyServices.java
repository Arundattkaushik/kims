package com.kims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.dto.RawPartyDTO;
import com.kims.entites.Party;
import com.kims.entites.RawSku;
import com.kims.repositories.RawPartyRepo;

@Service
public class RawPartyServices {

	@Autowired
	private RawPartyRepo rawPartyRepo;
	
	public Optional<RawPartyDTO> saveDto(Party party, RawSku rSku) {
		RawPartyDTO dto = new RawPartyDTO();
		dto.setRawSku(rSku);
		dto.setParty(party);
		return Optional.ofNullable(rawPartyRepo.save(dto));		
	}
	
	
	public Optional<List<RawPartyDTO>> rawPartyList(){
		return Optional.ofNullable(rawPartyRepo.findAll());
	}
	
	
	public Optional<List<RawPartyDTO>> findByParty(Party party){
		return Optional.ofNullable(rawPartyRepo.findByParty(party));
	}
	
	
	public Optional<List<RawPartyDTO>> findByRawSku(RawSku sku){
		return Optional.ofNullable(rawPartyRepo.findByRawSku(sku));
	}
}
