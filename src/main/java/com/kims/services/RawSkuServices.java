package com.kims.services;

import java.util.List;
import java.util.Optional;

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
		return rawSkuRepository.findAll();
	}
	
	public Boolean deleteRawSkuById(int rawSkuId) {
		rawSkuRepository.deleteById(rawSkuId);
		return true;
	}
	
	public RawSku getRawSkuByTitle(String title) {
		return rawSkuRepository.getSkuByTitle(title);
	}
	
	public Optional<RawSku> getById(Long rawSku_id) {
		return Optional.ofNullable(rawSkuRepository.getById(rawSku_id));
	}
	
	public void updateRawSkuQuantity(int rQty, Long rId) {
		
		rawSkuRepository.updateQuantity(rQty, rId);
	}
}
