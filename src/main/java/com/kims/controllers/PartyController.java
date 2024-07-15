package com.kims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import jakarta.servlet.http.HttpSession;




@Controller
public class PartyController {

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
	
}
