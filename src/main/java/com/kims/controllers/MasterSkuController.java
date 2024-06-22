package com.kims.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.kims.entites.MasterSku;
import com.kims.entites.RawSku;
import com.kims.entites.User;
import com.kims.services.MasterSkuServices;
import com.kims.services.RawSkuServices;
import com.kims.utilities.Utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MasterSkuController {
	private Boolean qtyStatus = false;
	@Autowired
	private Utils utils;
	@Autowired
	private MasterSkuServices mSkuServices;
	@Autowired
	private RawSkuServices rawSkuServices;
	
	
	private int stockValue = 0;

	@GetMapping("/master-sku-list")
	public String masterSkuList(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} else {
			return "master-sku-list";
		}
	}

	@GetMapping("/new-master-sku")
	public String newMasterSku(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} else {
			model.addAttribute("qtyStatus", qtyStatus);
			return "new-master-sku";
		}
	}

	@PostMapping("/process-new-master-sku")
	public String postMethodName(@ModelAttribute("newMaster") MasterSku newMaster, HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			return "redirect:/home";
		} 
		else {
			
			/* This line will return us a list of Ordered Raw SKUs */
			List<RawSku> rawSkus = utils.getRawSkuListByTitle(newMaster.getRawSku());
			
			/* This line will return us a list of left over Raw Sku after order placed successfully */
			List<Integer> remainingRawSkuQuantity = utils.canCreateMasterSku(utils.getRawSkuListByTitle(newMaster.getRawSku()),
					newMaster.getRawskuquantity(), newMaster.getMasterSkuQty());
			
			/* This check here is telling us that if the size of 'remainingRawSkuQuantity' list is 0. It clearly means that
			 * ordered RawSkus's quantity is either equal or less than available quantity in database. So order can be placed 
			 * successfully.
			*/
			if (remainingRawSkuQuantity.size() == 0) {
				
				/*
				 * This loop is giving us all those Raw-Skus which are ordered more than their available quantity.
				*/
				for (int i=0; i<rawSkus.size(); i++) {
					/* Get ordered Raw Sku at i-th order */
					RawSku sku = rawSkus.get(i);
					
					/* Get available quantity of Raw Sku at i-th order */
					int avlQty = Integer.parseInt(sku.getQuantity());
					
					/* Get ordered quantity of Raw Sku at i-th order */
					int ordQty = Integer.parseInt(newMaster.getRawskuquantity()[i])*newMaster.getMasterSkuQty();
					
					/* Calculating remaining quantity of Raw Sku at i-th order */
					int remQty = avlQty - ordQty;
					
					/*
					 * Check points
					 */
//					System.out.println("Quantity of raw sku when it was ordered: "+sku.getQuantity());
					sku.setQuantity(String.valueOf(remQty));
					
//					System.out.println("Quantity after raw sku's order placed: "+sku.getQuantity());
//					System.out.println(sku);
					stockValue += ordQty*Integer.parseInt(sku.getPrice_per_unit());
					rawSkuServices.createRawSku(sku);
				}
				
				newMaster.setStockValue(String.valueOf(stockValue));
				MasterSku mSku = mSkuServices.saveMasterSku(newMaster);
				stockValue = 0;
				List<MasterSku> mSkuList = new ArrayList<MasterSku>();
				mSkuList.add(mSku);
				session.setAttribute("mSkuList", mSkuServices.getAllMasterSkus());
				session.setAttribute("rawSkuList", rawSkuServices.getRawSkuList());
				return "redirect:/master-sku-list";
			} 
			else {
				session.setAttribute("exceedValue", remainingRawSkuQuantity);
				session.setAttribute("newMaster", newMaster);				
				session.setAttribute("rawSkus", rawSkus);
				return "redirect:/error-new-master-sku";
			}

		}
	}
	
	
	@GetMapping("/delete-master-sku")
	public String deleteMasterSku(@RequestParam("master_id") int master_id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {
			MasterSku mSku = mSkuServices.getMasterSkuById(master_id);
			List<RawSku> lSkus = utils.getRawSkuListByTitle(mSku.getRawSku());
			
			for (int i = 0; i < lSkus.size(); i++) {
				
				int q = Integer.parseInt(mSku.getRawskuquantity()[i])*mSku.getMasterSkuQty();
				RawSku rSku = lSkus.get(i);
				rSku.setQuantity(String.valueOf(q+Integer.parseInt(rSku.getQuantity())));
				rawSkuServices.createRawSku(rSku);
				q=0;
			}
			mSkuServices.deleteMasterSkuById(master_id);
			session.setAttribute("mSkuList", mSkuServices.getAllMasterSkus());
			session.setAttribute("rawSkuList", rawSkuServices.getRawSkuList());
			return "redirect:/master-sku-list";
		}
	}
	
	
	
	@GetMapping("/error-new-master-sku")
	public String errorNewMasterSku() {
		
		return "error-new-master-sku";
	}
	
	
	@GetMapping("/summary-master-sku")
	public String summaryMasterSku(@RequestParam("mSku_id") int mSku_id, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/home";
		} 
		else {
		model.addAttribute("mSummary", mSkuServices.getMasterSkuById(mSku_id));
		return "summary-master-sku";
		}
	}

}
