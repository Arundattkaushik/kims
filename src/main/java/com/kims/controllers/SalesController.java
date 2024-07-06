package com.kims.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kims.entites.MasterSku;
import com.kims.entites.RawSku;
import com.kims.entites.SalesInvoice;
import com.kims.entites.SoldRawSku;
import com.kims.entites.User;
import com.kims.services.MasterSkuServices;
import com.kims.services.RawSkuServices;
import com.kims.services.SalesService;
import com.kims.services.SoldRawSkuServices;

import jakarta.servlet.http.HttpSession;





@Controller
public class SalesController {
	@Autowired
	private RawSkuServices rawSkuServices;
	@Autowired
	private MasterSkuServices masterSkuServices;
	@Autowired
	private SoldRawSkuServices soldRawSkuServices;
	@Autowired 
	private SalesService salesService;

	@GetMapping("/sales")
	public String getMethodName(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "sales";
		}
	}
	
	
	@GetMapping("/new-invoice")
	public String newSaleOrder(HttpSession session) {
		User user = (User)session.getAttribute("user");
		if (user==null) {
			return "redirect:/home";
		}
		else {
			return "new-invoice";
		}
	}
	
	
	@PostMapping("/sales-invoice")	
	public String salesInvoice(@ModelAttribute("salesInvoice") SalesInvoice salesInvoice, HttpSession session) {
		User user = (User)session.getAttribute("user");		
		if (user==null) {
			return "redirect:/home";
		}
		else {
			
			for (int i = 0; i < salesInvoice.getMasterSku().length; i++) {
				MasterSku mSku = masterSkuServices.getMasterByMasterTitle(salesInvoice.getMasterSku()[i]);
				
				if (mSku !=null) {
					//Getting the array of RawSkus title present in MasterSku present at i-th index.
					String[] rSkus = mSku.getRawSku();
					
					//Getting the array of RawSkus quantity present in masterSku present at i-th index.
					int[] rSkuQty = mSku.getRawskuquantity();
					
					for (int j = 0; j < rSkus.length; j++) {
						int ordQty = 0;
						String rs = rSkus[j];
						RawSku rSku = rawSkuServices.getRawSkuByTitle(rs);
						SoldRawSku soldRawSku = new SoldRawSku();
						
							soldRawSku.setDate(salesInvoice.getInvDate());
							soldRawSku.setQuantity(rSkuQty[j]*salesInvoice.getQty()[i]);
							soldRawSku.setRSku(rs.trim());
							soldRawSkuServices.saveSoldRawSku(soldRawSku);						
						
						int avlQty = rSku.getQuantity();
						ordQty = rSkuQty[j]*salesInvoice.getQty()[i];
						int remQty = avlQty - ordQty;
						
						//Updating Raw SKU quantity in Raw Sku Table where id =:?
						rawSkuServices.updateRawSkuQuantity(remQty, rSku.getId());
						salesService.saveSalesInvoice(salesInvoice);
					}
					
				} 			
				
			}
			
			return "redirect:/sales";
		}
	}
	
	
}
