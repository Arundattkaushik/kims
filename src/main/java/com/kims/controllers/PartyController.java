package com.kims.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kims.entites.Party;
import com.kims.entites.User;
import com.kims.services.PartyServices;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PartyController {
	@Autowired
	private PartyServices partyServices;

	@GetMapping("/party-list")
	public String partyList(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "party-list";
		}
	}
	
	
	@GetMapping("/new-party")
	public String newParty(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "new-party";
		}
	}
	
	
	@PostMapping("/create-new-party")
	public String processNewParty(@ModelAttribute("newParty") Party party, HttpSession session, Model model) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			partyServices.createParty(party);			
			session.setAttribute("partyList", partyServices.getParties());
			return "redirect:/party-list";
			
		}
	}
	
	
	@GetMapping("/delete-party")
	public String deletePartyById(@RequestParam("party_id") int party_id, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			partyServices.deletePartyById(party_id);
			List<Party> partyList = partyServices.getParties();
			session.setAttribute("partyList", partyList);
			return "redirect:/party-list";
		}
	}
	
	
	
	
}
