package com.kims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.Party;
import com.kims.repositories.PartyRepository;

@Service
public class PartyServices {
	@Autowired
	private PartyRepository partyRepository;
	
	public Optional<Party> createParty(Party party) {
		return Optional.ofNullable(partyRepository.save(party));
	}
	
	public Optional<Party> findPartyById(Long pId) {
		return Optional.ofNullable(partyRepository.findById(pId));
	}
	
	public Optional<List<Party>> getParties(){
		return Optional.ofNullable(partyRepository.getAllParties());
	}
	
	public Optional<Boolean> deletePartyById(int partyId) {
		partyRepository.deleteById(partyId);
		return Optional.of(true);
	}
	
	
	public Party getPartyFromTitle(String pTitle) {
		return partyRepository.getPartyFromTitle(pTitle);
	}

	
//	public List<RawSku> getRawSkuAndParty(){
//		return partyRepository.getRawSkuAndParty();
//	}
}
