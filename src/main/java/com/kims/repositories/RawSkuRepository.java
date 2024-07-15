package com.kims.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.kims.entites.RawSku;

public interface RawSkuRepository extends JpaRepository<RawSku, Integer> {

	@Query("SELECT r FROM RawSku r order by r.id desc")
	public List<RawSku> getAllRawSkus();
	
	@Query("SELECT r FROM RawSku r WHERE r.name=:title")
	public RawSku getSkuByTitle(@Param("title") String title);
	
	@Query("SELECT r FROM RawSku r WHERE r.id=:id")
	public RawSku getById(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE RawSku r SET r.quantity=:rQty WHERE r.id=:rId")
	public void updateQuantity(@Param("rQty") int rQty, @Param("rId") Long rId);
	
}
