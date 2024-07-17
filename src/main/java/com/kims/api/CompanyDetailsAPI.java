package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.CompanyDetails;
import com.kims.services.CompanyDetailService;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/company")
public class CompanyDetailsAPI {
	@Autowired
	private CompanyDetailService companyDetailService;

	@GetMapping("/detail")
	public ResponseEntity<Object> getCompanyDetails(HttpSession session) {
		
		CompanyDetails cd = companyDetailService.details();
		if (cd == null) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "f");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			
			return new ResponseEntity<>(map, HttpStatus.OK);
		} 
		else {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", cd);
			
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		
	}
	
	
	
	@PostMapping("/update")
	public ResponseEntity<Object> update(@ModelAttribute("companyDetails") CompanyDetails companyDetails) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			CompanyDetails cDetails = companyDetailService.details();
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
			cDetails.setHudyamDl(companyDetails.getHudyamDl());
			
			CompanyDetails cd = companyDetailService.save(cDetails);
			if (cd == null) {
				
				map.put("message", "f");
				map.put("status", HttpStatus.NO_CONTENT);
				
				return 	new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
			} 
			else {
				map.put("message", "s");
				map.put("data", cd);
				map.put("status", HttpStatus.OK);
				
				return 	new ResponseEntity<>(map, HttpStatus.OK);
			}
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			return 	new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
}
