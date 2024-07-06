package com.kims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kims.entites.CompanyDetails;
import com.kims.entites.User;
import com.kims.services.CompanyDetailService;

import jakarta.servlet.http.HttpSession;



@Controller
public class CompanyDetail {
	@Autowired
	private CompanyDetailService companyDetailService;

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
	
	
	@PostMapping("/update-company-details")
	public String updateCompanyDetails(@ModelAttribute("companyDetails") CompanyDetails companyDetails, HttpSession session) {
		try {
			User user = (User)session.getAttribute("user");
			if (user==null) {
				return "redirect:/home";
			}
			
			CompanyDetails cDetails = companyDetailService.getCompanyDetails();
			if (cDetails==null) {
				cDetails = new CompanyDetails();
			} 
			cDetails.setCompanyName(companyDetails.getCompanyName());
			cDetails.setCompanyOwner(companyDetails.getCompanyOwner());
			cDetails.setCompanyMailingAddress(companyDetails.getCompanyMailingAddress());
			cDetails.setCompanyContactNo(companyDetails.getCompanyContactNo());
			cDetails.setCompanyEmail(companyDetails.getCompanyEmail());
			cDetails.setAccountType(companyDetails.getAccountType());
			cDetails.setAdditionalContactNo(companyDetails.getAdditionalContactNo());
			cDetails.setBankAccountNumber(companyDetails.getBankAccountNumber());
			cDetails.setBankName(companyDetails.getBankName());
			cDetails.setBankIfscCode(companyDetails.getBankIfscCode());
			cDetails.setCity(companyDetails.getCity());
			cDetails.setState(companyDetails.getState());
			cDetails.setZipCode(companyDetails.getZipCode());
			cDetails.setCountry(companyDetails.getCountry());
			cDetails.setTermsAndConditions(companyDetails.getTermsAndConditions());
			cDetails.setGstin(companyDetails.getGstin());
			companyDetailService.saveCompanyDetails(cDetails);
			return 	"redirect:/company-details";
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
		
		
	}
	
	
}
