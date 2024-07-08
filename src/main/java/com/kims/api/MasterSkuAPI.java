package com.kims.api;

import org.springframework.web.bind.annotation.RestController;
import com.kims.entites.MasterSku;
import com.kims.services.MasterSkuServices;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		Optional<Boolean> op = Optional.of(mSkuServices.deleteMasterSkuById(Integer.parseInt(request.getParameter("id"))));
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (op.get()==true) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("status", HttpStatus.CONFLICT);
			return new ResponseEntity<Object>(map, HttpStatus.CONFLICT);
		}		
		
	}
	
	
	@PostMapping("/create-master")
	public ResponseEntity<Object> createMasterSku(@ModelAttribute MasterSku mSku) {
		Optional<MasterSku> op = Optional.ofNullable(mSkuServices.saveMasterSku(mSku));
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("status", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	
	
	@PostMapping("/get-master-by-id")
	public ResponseEntity<Object> getMasterById(HttpServletRequest request) {
		Optional<MasterSku> op = Optional.ofNullable(mSkuServices.getMasterSkuById(Integer.parseInt(request.getParameter("id"))));
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("status", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}	
		
	}
	
	
	
	
	
}
