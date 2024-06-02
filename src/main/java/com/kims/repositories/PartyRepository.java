package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kims.entites.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {

	@Query("SELECT p FROM Party p")
	public List<Party> getAllParties();
}
