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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/msku")
public class MasterSkuAPI {
	@Autowired
	private MasterSkuServices mSkuServices;
	
	@PostMapping("/get-mSku-desc")
	public List<MasterSku> getMasterSkuDescByTitle(HttpServletRequest request) {		
		return null;
	}
	
	@PostMapping("/avl-qty")
	public int getMasterSkuQtyByTitle() {		
		return 1;
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> mSkuList() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		map.put("data", mSkuServices.getAllMasterSkus());
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}	
	
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteMaster(HttpServletRequest request) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Optional<Boolean> op = Optional.of(mSkuServices.deleteMasterSkuById(Integer.parseInt(request.getParameter("id"))));
		
		if (op.get()==true) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
	

	
	@PostMapping("/create")
	public ResponseEntity<Object> createMasterSku(@ModelAttribute MasterSku sku) {	
		Map<String, Object> map = new HashMap<String, Object>();		
		Optional<MasterSku> mSku = mSkuServices.saveMasterSku(sku);
		
		if (mSku.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", mSku.get());
			
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		
	}
	
	
	
	@PostMapping("/get")
	public ResponseEntity<Object> getMasterById(HttpServletRequest request) {
		Optional<MasterSku> op = Optional.ofNullable(mSkuServices.getMasterSkuById(Long.valueOf(request.getParameter("id"))));
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
