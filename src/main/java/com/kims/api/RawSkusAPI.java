package com.kims.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.kims.entites.RawSku;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RawSkusAPI {
	@Autowired
	private RawSkuServices rawSkuServices;
	
	@GetMapping("/get-raw-sku-list")
	public List<RawSku>  rawSkuList(HttpSession session) {
		
		return rawSkuServices.getRawSkuList();
	}
	
	
}
