package com.kims.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kims.dto.RawPartyDTO;
import com.kims.services.RawPartyServices;

@RestController
@RequestMapping("/raw-party")
public class RawPartyDtoAPI {
	@Autowired
	private RawPartyServices rpServ;
	
	@GetMapping("/list")
	public ResponseEntity<Object> rawPartyList() {
		Map<String, Object> map = new HashMap<String, Object>();

		Optional<List<RawPartyDTO>> list = rpServ.rawPartyList();

		if (list.isPresent()) {

			map.put("message", "s");
			map.put("status", HttpStatus.OK);
			map.put("data", list.get());

			return new ResponseEntity<Object>(map, HttpStatus.OK);

		} else {

			map.put("message", "f");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);

			return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
