package com.kims.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.MasterSku;
import com.kims.repositories.MasterSkuRepository;

@Service
public class MasterSkuServices {

	@Autowired
	private MasterSkuRepository masterSkuRepository;
	
	public MasterSku saveMasterSku(MasterSku masterSku) {
		return masterSkuRepository.save(masterSku);
	}
	
	public MasterSku getMasterSkuById(int masterId) {
		return masterSkuRepository.getMasterSkuById(masterId);
	}
	
	public List<MasterSku> getAllMasterSkus(){
		return masterSkuRepository.getAllMasterSkus();
	}
	
	public void deleteMasterSkuById(int master_id) {
		masterSkuRepository.deleteById(master_id);
	}
}
