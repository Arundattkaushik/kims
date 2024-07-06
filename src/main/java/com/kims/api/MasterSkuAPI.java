package com.kims.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kims.entites.MasterSku;
import com.kims.services.MasterSkuServices;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ProjectedPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;




@RestController
public class MasterSkuAPI {
	@Autowired
	MasterSkuServices mSkuServices;
	
	@PostMapping("/get-mSku-desc")
	public List<MasterSku> getMasterSkuDescByTitle(HttpServletRequest request) {		
		return mSkuServices.getMasterDescriptionByMasterTitle(request.getParameter("title"));
	}
	
	@PostMapping("/get-master-avl-qty")
	public int getMasterSkuQtyByTitle() {		
		return 1;
	}
	
	@GetMapping("/msku-list")
	public ResponseEntity<Object> mSkuList() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		map.put("data", mSkuServices.getAllMasterSkus());
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@PostMapping("/delete-master")
	public ResponseEntity<Object> deleteMaster(HttpServletRequest request) {
		
		mSkuServices.deleteMasterSkuById(Integer.parseInt(request.getParameter("id")));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	
	@PostMapping("/create-master")
	public ResponseEntity<Object> createMasterSku(@ModelAttribute MasterSku mSku) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		map.put("data", mSkuServices.saveMasterSku(mSku));
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	
	
	
	
}
