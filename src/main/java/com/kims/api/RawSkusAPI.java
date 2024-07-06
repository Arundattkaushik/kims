package com.kims.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.kims.entites.RawSku;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class RawSkusAPI {
	@Autowired
	private RawSkuServices rawSkuServices;
	
	@GetMapping("/get-raw-sku-list")
	public ResponseEntity<Object> rawSkuList(HttpSession session) {
//		session.setAttribute("rSkuList", rawSkuServices.getRawSkuList());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "success");
		map.put("status", HttpStatus.OK);
		map.put("data", rawSkuServices.getRawSkuList());
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	@PostMapping("/get-row-sku-by-id")
	public RawSku getRawSku(HttpServletRequest request) {
		return rawSkuServices.getRawSkuById(Integer.parseInt(request.getParameter("rId")));
	}
	
	
	@PostMapping("/update-row-sku-quantity")
	public void updateRawSkuQuantiy(HttpServletRequest request) {
		rawSkuServices.updateRawSkuQuantity(Integer.parseInt(request.getParameter("rQty")), Integer.parseInt(request.getParameter("rId")));
	}
	
	
}
