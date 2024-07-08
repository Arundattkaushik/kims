package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.kims.entites.User;
import com.kims.services.MasterSkuServices;
import com.kims.services.RawSkuServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MasterSkuController {
	private Boolean qtyStatus = false;
	@Autowired
	private MasterSkuServices mSkuServices;
	@Autowired
	private RawSkuServices rawSkuServices;

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
	
	
	@GetMapping("/delete-master-sku")
	public String deleteMasterSku(@RequestParam("master_id") int master_id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {
			
			mSkuServices.deleteMasterSkuById(master_id);
			session.setAttribute("mSkuList", mSkuServices.getAllMasterSkus());
			session.setAttribute("rawSkuList", rawSkuServices.getRawSkuList());
			return "redirect:/master-sku-list";
		}
	}
	
	
	
	@GetMapping("/error-new-master-sku")
	public String errorNewMasterSku() {
		
		return "error-new-master-sku";
	}
	
	
	@GetMapping("/summary-master-sku")
	public String summaryMasterSku(@RequestParam("mSku_id") int mSku_id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {
		model.addAttribute("mSummary", mSkuServices.getMasterSkuById(mSku_id));
		return "summary-master-sku";
		}
	}

}
