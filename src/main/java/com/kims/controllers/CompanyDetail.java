package com.kims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import jakarta.servlet.http.HttpSession;



@Controller
public class CompanyDetail {

	@GetMapping("/company-details")
	public String viewCompanyDetails(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "/company-details";
		}	
	}	
	
}
