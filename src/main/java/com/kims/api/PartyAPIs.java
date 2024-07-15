package com.kims.api;

import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.Party;
import com.kims.services.PartyServices;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/party")
public class PartyAPIs {
	@Autowired
	private PartyServices partyServices;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> createParty(@ModelAttribute Party party) {		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Optional<Party> op = partyServices.createParty(party);
		
		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
			
		} else {
			map.put("message", "f");
			map.put("status", HttpStatus.METHOD_NOT_ALLOWED);			
			return new ResponseEntity<Object>(map, HttpStatus.METHOD_NOT_ALLOWED);

		}
	}
	
	
	
	
	@GetMapping("/list")
	public ResponseEntity<Object> partyList() {
		Map<String, Object> map = new HashMap<String, Object>();
		Optional<List<Party>> op = partyServices.getParties();
		
		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
			
		} else {
			map.put("message", "f");
			map.put("status", HttpStatus.BAD_REQUEST);			
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);

		}
	}
	
	
	
	
	@PostMapping("/get")
	public ResponseEntity<Object> partyById(HttpServletRequest request) {		
		Map<String, Object> map = new HashMap<String, Object>();
		Long pId = Long.valueOf(request.getParameter("id"));
		Optional<Party> op = partyServices.findPartyById(pId);
		
		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
			
		} else {
			map.put("message", "f");
			map.put("status", HttpStatus.METHOD_NOT_ALLOWED);			
			return new ResponseEntity<Object>(map, HttpStatus.METHOD_NOT_ALLOWED);

		}
	}
	
	
	
	
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteParty(HttpServletRequest request) {
		Optional<Boolean> op = partyServices.deletePartyById(Integer.parseInt(request.getParameter("pId")));
		Map<String, Object> map = new HashMap<String, Object>();
		if (op.get()==true) {
			map.put("message", "s");
			map.put("data", true);
			map.put("status", HttpStatus.OK);
			
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} 
		else {
			map.put("message", "f");
			map.put("data", false);
			map.put("status", HttpStatus.CONFLICT);
			
			return new ResponseEntity<Object>(map, HttpStatus.CONFLICT);
		}
			
	}	
	
	

}
