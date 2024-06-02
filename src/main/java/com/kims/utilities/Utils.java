package com.kims.utilities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.entites.RawSku;
import com.kims.services.RawSkuServices;

@Service
public class Utils {
	@Autowired
	private RawSkuServices rawSkuServices;
	
	public List<RawSku> getRawSkuListByTitle(String[] arr){
		List<RawSku> rawSkus = new ArrayList<RawSku>();
		for (int i = 0; i < arr.length; i++) {
			RawSku sku = rawSkuServices.getRawSkuByTitle(arr[i].trim());
			if (sku != null) {
				rawSkus.add(sku);
			}
		}
		return rawSkus;
	}
	
	
	public List<Integer> canCreateMasterSku(List<RawSku> list, String[] arr, int masterSkuQty){
		List<Integer> l = new ArrayList<Integer>();		
		for (int i = 0; i < arr.length; i++) {
			
			if(arr[i].isEmpty()) {
				
				return l;
			}
			
			if(Integer.parseInt(arr[i])*masterSkuQty > Integer.parseInt(list.get(i).getQuantity())) {
				l.add(Integer.parseInt(arr[i])*masterSkuQty - Integer.parseInt(list.get(i).getQuantity()));
			}			
		}
		return l;
	}
	
	
}
