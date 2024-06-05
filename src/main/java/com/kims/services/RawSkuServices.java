package com.kims.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.RawSku;
import com.kims.repositories.RawSkuRepository;

@Service
public class RawSkuServices {
	@Autowired
	private RawSkuRepository rawSkuRepository;
	
	public RawSku createRawSku(RawSku rawSku) {
		return rawSkuRepository.save(rawSku);
	}
	
	public List<RawSku> getRawSkuList(){
		return rawSkuRepository.getAllRawSkus();
	}
	
	public void deleteRawSkuById(int rawSkuId) {
		rawSkuRepository.deleteById(rawSkuId);
	}
	
	public RawSku getRawSkuByTitle(String title) {
		return rawSkuRepository.getSkuByTitle(title);
	}
	
	public RawSku getRawSkuById(int rawSku_id) {
		return rawSkuRepository.getSkuById(rawSku_id);
	}
}
