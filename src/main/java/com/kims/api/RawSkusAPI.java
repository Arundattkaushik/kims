package com.kims.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.kims.dto.RawPartyDTO;
import com.kims.entites.Party;
import com.kims.entites.RawSku;
import com.kims.services.PartyServices;
import com.kims.services.RawPartyServices;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rawsku")
public class RawSkusAPI {
	@Autowired
	private RawSkuServices rawSkuServices;
	@Autowired
	private PartyServices pServices;
	@Autowired
	private RawPartyServices rawPartyServices;

	@GetMapping("/list")
	public ResponseEntity<Object> rawSkuList() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<RawSku> list = rawSkuServices.getRawSkuList();


			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", list);

			return new ResponseEntity<Object>(map, HttpStatus.OK);

	}
		
		
		

	@PostMapping("/get")
	public ResponseEntity<Object> getRawSkuById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		Optional<RawSku> op = rawSkuServices.getById(Long.valueOf(request.getParameter("rId")));

		if (op.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", op.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);

		} else {
			map.put("message", "f");
			map.put("data", "no record found with this Id");
			map.put("status", HttpStatus.NOT_FOUND);
			return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteRawSkuById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Integer.parseInt(request.getParameter("rId"))
		Optional<List<RawPartyDTO>> st = rawPartyServices.findByRawSku(
				rawSkuServices.getById(Long.valueOf(Integer.parseInt(request.getParameter("rId")))).get());

		if (st.isEmpty()) {
			rawSkuServices.deleteRawSkuById(Integer.parseInt(request.getParameter("rId")));
			map.put("message", "s");
			map.put("status", HttpStatus.OK);

			return new ResponseEntity<Object>(map, HttpStatus.OK);
		} else {
			map.put("message", "can't an item if its attached to a party");
			map.put("status", HttpStatus.FORBIDDEN);
			return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping("/update")
	public void updateRawSkuQuantiy(HttpServletRequest request) {
		rawSkuServices.updateRawSkuQuantity(Integer.parseInt(request.getParameter("rQty")),
				Long.valueOf(request.getParameter("rId")));
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createRawSku(@ModelAttribute RawSku rSku) {
		Map<String, Object> map = new HashMap<String, Object>();		
		Optional<RawPartyDTO> newDto = Optional.empty();
		Optional<Party> party = pServices.findPartyById(rSku.getPartyId());
		Optional<RawSku> sku = Optional.ofNullable(rawSkuServices.createRawSku(rSku));
		

		if (sku.isPresent()) {
			
			if (party.isPresent()) {
				newDto = rawPartyServices.saveDto(party.get(), sku.get());				
			} 
		}
		
		if (newDto.isPresent()) {
			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", newDto.get());
			return new ResponseEntity<Object>(map, HttpStatus.OK);
			
		}
		else {
			map.put("message", "f");
			map.put("data", "no party found with this Id");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}

}
