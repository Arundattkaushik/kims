package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.services.MasterSkuServices;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class MasterSkuAPI {
	@Autowired
	MasterSkuServices mSkuServices;
	
	@PostMapping("/get-mSku-desc")
	public String getMasterSkuDescByTitle(HttpServletRequest request) {		
		return mSkuServices.getMasterDescriptionByMasterTitle(request.getParameter("title"));
	}
	
	
}
