package com.kims.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kims.entites.MasterSku;
import com.kims.entites.RawSku;
import com.kims.entites.SalesInvoice;
import com.kims.entites.SoldRawSku;
import com.kims.services.MasterSkuServices;
import com.kims.services.PartyServices;
import com.kims.services.RawSkuServices;
import com.kims.services.SalesService;
import com.kims.services.SoldRawSkuServices;
import com.kims.utilities.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/sale-invoice")
public class SalesInvoiceAPI {
	@Autowired
	private SalesService service;
	@Autowired
	private MasterSkuServices mServices;
	@Autowired
	private RawSkuServices rServices;
	@Autowired
	private SoldRawSkuServices soldRawSkuServices;
	@Autowired
	private PartyServices pServices;
	@Autowired
	private Utils utils;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@ModelAttribute SalesInvoice invoice) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (invoice.getBillToName()==null || invoice.getBillToName() =="") {
			invoice.setBillToName(pServices.findPartyById(Long.valueOf(invoice.getSelect_billTo_party())).get().getName());
		}
		
		if (invoice.getShippToName()==null || invoice.getShippToName() =="") {
			invoice.setShippToName(pServices.findPartyById(Long.valueOf(invoice.getSelect_shipTo_party())).get().getName());
		}
		
		//Saving grand total in words before persisting sales invoice in db
		invoice.setGTotalInWords(Utils.convert(invoice.getFgTotal().longValue()));
		Optional<SalesInvoice> sInvoice = service.save(invoice);
		
		try {
			
			//fetching Ids of all master skus attached to a Sales Invoice.
			for (int i=0; i<sInvoice.get().getMasterSku().size(); i++) {
				MasterSku mSku = mServices.getMasterSkuById(sInvoice.get().getMasterSku().get(i));
				
				for (int j = 0; j < mSku.getOrdRawSkuQty().size(); j++) {
					SoldRawSku srs = new SoldRawSku();
					RawSku r = rServices.getById(mSku.getRSkuIds().get(j)).get();
					srs.setRId(r.getId());
					srs.setName(r.getName());
					
					int soldQty = mSku.getOrdRawSkuQty().get(j)*sInvoice.get().getQty().get(i);
					int remQty = r.getQuantity()-soldQty;
					
					srs.setSoldQty(soldQty);
					rServices.updateRawSkuQuantity(remQty, r.getId());
					
					//Clearing chache to ensure loading fresh RawSku next time.
					utils.clearCache();
					soldRawSkuServices.save(srs);
					
					
				}
				
			}
			
			if (sInvoice.isPresent()) {
				map.put("message", "s");
				map.put("status", HttpStatus.OK);
				map.put("data", sInvoice.get());
				return new ResponseEntity<Object>(map, HttpStatus.OK);
				
			} else {
				map.put("message", "f");
				map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);			
				return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			
		} 
		
		catch (Exception e) {
			map.put("message", "f");
			map.put("status", HttpStatus.BAD_REQUEST);			
			map.put("data", "Something went wrong!");			
			return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	
	
}
