package com.kims.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kims.entites.MasterSku;

public interface MasterSkuRepository extends JpaRepository<MasterSku, Integer> {
	
	@Query("SELECT m FROM MasterSku m order by m.id desc")
	public List<MasterSku> getAllMasterSkus();
	
	@Query("SELECT m FROM MasterSku m WHERE m.id=:x")
	public MasterSku getMasterSkuById(@Param("x") int masterId);
	
	@Query("SELECT new MasterSku(masterSkuDescription, hsn) m FROM MasterSku m WHERE m.masterSkuTitle=:title")
	public List<MasterSku> getDescriptionFromSkuTitle(@Param("title") String masteTitle);
	
	@Query("SELECT m FROM MasterSku m WHERE m.masterSkuTitle=:title")
	public MasterSku getMasterByTitle(@Param("title") String masteTitle);

}
