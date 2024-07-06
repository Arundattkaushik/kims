package com.kims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class PurchaseController {
	
	@GetMapping("/purchase")
	public String viewPurchaseList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {

			return "purchase";
		}
	}
	
	
	@GetMapping("/new-purchase-invoice")
	public String newPurchaseList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {

			return "new-purchase-invoice";
		}
	}

}
