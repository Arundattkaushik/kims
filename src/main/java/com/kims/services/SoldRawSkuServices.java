package com.kims.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.SoldRawSku;
import com.kims.repositories.SoldRawSkuRepo;

@Service
public class SoldRawSkuServices {
	@Autowired
	private SoldRawSkuRepo soldRawSkuRepo;
	
	public Optional<SoldRawSku> findSoldRawSkuById(int srsId) {
		return soldRawSkuRepo.findById(srsId);
	}
	
	public SoldRawSku save(SoldRawSku soldRawSku){
		return soldRawSkuRepo.save(soldRawSku);
	}

}
