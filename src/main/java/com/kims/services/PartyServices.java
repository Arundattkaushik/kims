package com.kims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.Party;
import com.kims.repositories.PartyRepository;

@Service
public class PartyServices {
	@Autowired
	private PartyRepository partyRepository;
	
	public Party createParty(Party party) {
		return partyRepository.save(party);
	}
	
	public List<Party> getParties(){
		return partyRepository.getAllParties();
	}
	
	public void deletePartyById(int partyId) {
		partyRepository.deleteById(partyId);
	}
	
	public List<String> getListOfPartyTitles(){
		return partyRepository.getTitleListOfParties();
	}
	
	public Party getPartyFromTitle(String pTitle) {
		return partyRepository.getPartyFromTitle(pTitle);
	}

}
