package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RawSkuController {
	@Autowired
	private RawSkuServices rawSkuServices;
	
	
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

	
	
	@GetMapping("/delete_rawsku")
	public String deleteRawSkuById(@RequestParam("rawsku_id") int rawSkuId, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "home";
		}
		else {
			rawSkuServices.deleteRawSkuById(rawSkuId);
			session.setAttribute("rSkuList", rawSkuServices.getRawSkuList());
			return "redirect:/raw-sku-list";
		}
	}
	
	
	
	
}
