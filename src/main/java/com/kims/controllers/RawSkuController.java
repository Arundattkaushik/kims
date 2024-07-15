package com.kims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import jakarta.servlet.http.HttpSession;


@Controller
public class RawSkuController {	
	
	@GetMapping("/raw-sku-list")
	public String rawSkuList(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "home";
		} 
		else {
			return "raw-sku-list";
		}
	}
	
	@GetMapping("/new-raw-sku")
	public String newRawSku(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "home";
		}
		else {
			return "new-raw-sku";
		}
	}
	
}
