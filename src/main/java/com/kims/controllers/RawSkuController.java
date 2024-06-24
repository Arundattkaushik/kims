package com.kims.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.kims.entites.RawSku;
import com.kims.entites.User;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RawSkuController {
	@Autowired
	private RawSkuServices rawSkuServices;
	
	
	@GetMapping("/raw-sku-list")
	public String rawSkuList() {
		return "raw-sku-list";
	}
	
	@GetMapping("/new-raw-sku")
	public String newRawSku() {
		return "new-raw-sku";
	}
	
	
	@PostMapping("/create-raw-sku")
	public String createNewRawSku(@ModelAttribute("rawsku") RawSku rawSku, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "home";
		}
		else {
			
			rawSkuServices.createRawSku(rawSku);
			session.setAttribute("rSkuList", rawSkuServices.getRawSkuList());
			return "redirect:/raw-sku-list";
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
