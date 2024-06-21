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
	
	
	public List<Integer> canCreateMasterSku(List<RawSku> orderedRawSkus, String[] orderedRawSkuQuantity, int masterSkuQty){
		List<Integer> newList = new ArrayList<Integer>();
		
		for (int i = 0; i < orderedRawSkuQuantity.length; i++) {
			
			if(orderedRawSkuQuantity[i].isEmpty()) {
				
				return newList;
			}
			
			if(Integer.parseInt(orderedRawSkuQuantity[i])*masterSkuQty > Integer.parseInt(orderedRawSkus.get(i).getQuantity())) {
				newList.add(Integer.parseInt(orderedRawSkuQuantity[i])*masterSkuQty - Integer.parseInt(orderedRawSkus.get(i).getQuantity()));
			}			
		}
		return newList;
	}
	
	
}
