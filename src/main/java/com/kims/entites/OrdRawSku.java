package com.kims.entites;

import lombok.Data;

@Data
public class OrdRawSku {

	//Raw SKU Details attached to Master SKU
	private Long id;
	private String name;
	private int avlQty;
	private int ordQty;
	
}
