package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kims.entites.RawSku;

public interface RawSkuRepository extends JpaRepository<RawSku, Integer> {

	@Query("SELECT r FROM RawSku r")
	public List<RawSku> getAllRawSkus();
	
	@Query("SELECT r FROM RawSku r WHERE r.name=:title")
	public RawSku getSkuByTitle(@Param("title") String title);
	
	@Query("SELECT r FROM RawSku r WHERE r.id=:y")
	public RawSku getSkuById(@Param("y") int id);
	
}
