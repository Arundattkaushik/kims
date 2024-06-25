package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kims.entites.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	@Query("SELECT p FROM Party p")
	public List<Party> getAllParties();
	
	@Query("SELECT partyName p FROM Party p")
	public List<String> getTitleListOfParties();
	
	@Query("SELECT p FROM Party p Where p.partyName=:pTitle")
	public Party getPartyFromTitle(@Param("pTitle") String pTitle);
}
