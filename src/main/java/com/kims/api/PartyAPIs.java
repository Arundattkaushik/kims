package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.services.PartyServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class PartyAPIs {
	@Autowired
	private PartyServices partyServices;
	
	@GetMapping("/party-titles-list")
	public List<String> partyTitleList() {
		//System.out.println(partyServices.getListOfPartyTitles());
		return partyServices.getListOfPartyTitles();
	}
	

}
