package com.kims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kims.entites.CompanyDetails;

public interface CompanyDetailsRepo extends CrudRepository<CompanyDetails, Integer>, JpaRepository<CompanyDetails, Integer> {
	
	@Query("SELECT cd FROM CompanyDetails cd")
	public CompanyDetails getCompanyDetails();
	
}
