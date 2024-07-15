package com.kims.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.MasterSku;
import com.kims.repositories.MasterSkuRepository;

@Service
public class MasterSkuServices {

	@Autowired
	private MasterSkuRepository masterSkuRepository;
	
	public Optional<MasterSku> saveMasterSku(MasterSku sku) {
        return Optional.of(masterSkuRepository.save(sku));
    }
	
	public MasterSku getMasterSkuById(Long masterId) {
		return masterSkuRepository.getMasterSkuById(masterId);
	}
	
	public List<MasterSku> getAllMasterSkus(){
		return masterSkuRepository.getAllMasterSkus();
	}
	
	public Boolean deleteMasterSkuById(int master_id) {
		masterSkuRepository.deleteById(master_id);
		return true;
	}
	
}
