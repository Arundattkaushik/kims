package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.Party;
import com.kims.services.PartyServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
public class PartyAPIs {
	@Autowired
	private PartyServices partyServices;
	
	@GetMapping("/party-titles-list")
	public List<String> partyTitleList() {
		return partyServices.getListOfPartyTitles();
	}
	
	@GetMapping("/get-party-list")
	public void loadPartyDataInSession(HttpSession session) {
		session.setAttribute("partyList", partyServices.getParties());
	}
	
	@PostMapping("/get-bill-to-party")
	public Party getBillToSelectedParty(HttpServletRequest request) {
		return partyServices.getPartyFromTitle(request.getParameter("pTitle"));
	}
	
	@PostMapping("/get-ship-to-party")
	public Party getShipToSelectedParty(HttpServletRequest request) {
		return partyServices.getPartyFromTitle(request.getParameter("pTitle"));
	}
	

}
