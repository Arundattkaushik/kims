package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.kims.entites.User;
import com.kims.services.MasterSkuServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
public class SalesController {
	@Autowired
	private MasterSkuServices mSkuServices;

	@GetMapping("/sales")
	public String getMethodName(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "sales";
		}
	}
	
	
	@GetMapping("/new-sales-order")
	public String newSaleOrder(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "new-sales-order";
		}
	}
	
}
