package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import com.kims.services.MasterSkuServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MasterSkuController {
	private Boolean qtyStatus = false;
	@Autowired
	private MasterSkuServices mSkuServices;

	@GetMapping("/master-sku-list")
	public String masterSkuList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} else {
			return "master-sku-list";
		}
	}

	@GetMapping("/new-master-sku")
	public String newMasterSku(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} else {
			model.addAttribute("qtyStatus", qtyStatus);
			return "new-master-sku";
		}
	}
	
	
	
//	@GetMapping("/summary-master-sku")
//	public String summaryMasterSku(@RequestParam("mSku_id") int mSku_id, Model model, HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			return "redirect:/home";
//		} 
//		else {
//		model.addAttribute("mSummary", mSkuServices.getMasterSkuById(mSku_id));
//		return "summary-master-sku";
//		}
//	}

}
