package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.kims.entites.CompanyDetails;
import com.kims.repositories.CompanyDetailsRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;



@Controller
public class CompanyDetail {
	@Autowired
	private CompanyDetailsRepo companyDetailsRepo;

	@GetMapping("/company-details")
	public String viewCompanyDetails(HttpSession session) {
		return "company-details";
	}
	
	@PutMapping("/update-company-details")
	public String updateCompanyDetails(@ModelAttribute("companyDetails") CompanyDetails companyDetails, HttpSession session) {
		CompanyDetails cDetails = companyDetailsRepo.getCompanyDetails();
		int cId = cDetails.getId();
		System.out.println("cId before set:  "+companyDetails.getId());
		companyDetails.setId(cId);
		System.out.println("cId after set:  "+companyDetails.getId());
		companyDetailsRepo.save(companyDetails);
		return "redirect:/user-home";
	}
	
	
}
