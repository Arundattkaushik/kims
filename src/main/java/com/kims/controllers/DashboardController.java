package com.kims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class DashboardController {
	
	@GetMapping("/user-home")
	public String getMethodName(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "user-home";
		}
	}
	
}
