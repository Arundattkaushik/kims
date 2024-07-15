package com.kims.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.CompanyDetails;
import com.kims.repositories.CompanyDetailsRepo;

@Service
public class CompanyDetailService {
	@Autowired
	private CompanyDetailsRepo companyDetailsRepo;
	
	public CompanyDetails details() {
		return companyDetailsRepo.getCompanyDetails();
	}
	
	public CompanyDetails save(CompanyDetails companyDetails) {
		return companyDetailsRepo.save(companyDetails);
	}
	
}
