package com.kims.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kims.dto.RawMasterDTO;
import com.kims.entites.MasterSku;
import com.kims.entites.OrdRawSku;
import com.kims.entites.RawSku;
import com.kims.services.MasterSkuServices;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/raw-master")
public class RawMasterDtoAPI {
	@Autowired
	private MasterSkuServices mServices;
	@Autowired
	private RawSkuServices rServices;

	@GetMapping("/list")
	public ResponseEntity<Object> rawMasterList() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<MasterSku> mSkus = mServices.getAllMasterSkus();
		List<RawMasterDTO> list_rm_dto = new ArrayList<RawMasterDTO>();

		for (MasterSku m : mSkus) {
			RawMasterDTO dto = new RawMasterDTO();
			dto.setMId(m.getId());
			dto.setMTitle(m.getMSkuTitle());
			dto.setMDesc(m.getMSkuDesc());
			dto.setMHsn(m.getMHsn());
			dto.setDoc(m.getDoc());

			List<OrdRawSku> ordRawSkus = new ArrayList<OrdRawSku>();
			// Loop for fetching RawSKUs attached to each MasterSKU
			for (int i = 0; i < m.getRSkuIds().size(); i++) {
				RawSku r = rServices.getById(m.getRSkuIds().get(i)).orElse(null);

				if (r != null) {
					OrdRawSku or = new OrdRawSku();
					or.setId(r.getId());
					or.setName(r.getName());
					or.setOrdQty(m.getOrdRawSkuQty().get(i));
					or.setAvlQty(r.getQuantity());
					ordRawSkus.add(or);
				}
			}
			dto.setOrdRawSkus(ordRawSkus);
			list_rm_dto.add(dto);
		}

		map.put("message", "s");
		map.put("status", HttpStatus.OK);
		map.put("data", list_rm_dto);
		return new ResponseEntity<Object>(map, HttpStatus.OK);

	}
	
	
	

	@PostMapping("/get")
	public ResponseEntity<Object> getRowMasterDTO(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long mId = Long.valueOf(request.getParameter("id"));
		MasterSku mSku = mServices.getMasterSkuById(mId);
		
		List<RawMasterDTO> list_rm_dto = new ArrayList<RawMasterDTO>();


			RawMasterDTO dto = new RawMasterDTO();
			dto.setMId(mSku.getId());
			dto.setMTitle(mSku.getMSkuTitle());
			dto.setMDesc(mSku.getMSkuDesc());
			dto.setMHsn(mSku.getMHsn());
			dto.setDoc(mSku.getDoc());

			List<OrdRawSku> ordRawSkus = new ArrayList<OrdRawSku>();
			// Loop for fetching RawSKUs attached to each MasterSKU
			for (int i = 0; i < mSku.getRSkuIds().size(); i++) {
				RawSku r = rServices.getById(mSku.getRSkuIds().get(i)).orElse(null);

				if (r != null) {
					OrdRawSku or = new OrdRawSku();
					or.setId(r.getId());
					or.setName(r.getName());
					or.setOrdQty(mSku.getOrdRawSkuQty().get(i));
					or.setAvlQty(r.getQuantity());
					ordRawSkus.add(or);
				}
			}
			dto.setOrdRawSkus(ordRawSkus);
			list_rm_dto.add(dto);


		map.put("message", "s");
		map.put("status", HttpStatus.OK);
		map.put("data", list_rm_dto);
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
