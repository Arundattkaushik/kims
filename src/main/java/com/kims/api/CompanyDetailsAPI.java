package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.CompanyDetails;
import com.kims.services.CompanyDetailService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CompanyDetailsAPI {
	@Autowired
	private CompanyDetailService companyDetailService;

	@GetMapping("/get-company-details")
	public CompanyDetails getCompanyDetails(HttpSession session) {
		session.setAttribute("compDetails", companyDetailService.getCompanyDetails());
		return companyDetailService.getCompanyDetails();
	}
	
}
