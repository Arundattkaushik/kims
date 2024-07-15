package com.kims.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kims.entites.OrdRawSku;

import lombok.Data;

@Data
public class RawMasterDTO {
	
	//Master SKU Details
	private Long mId;
	private String mTitle;
	private String mDesc;
	private String mHsn;
	private Date doc;
	private List<OrdRawSku> ordRawSkus = new ArrayList<OrdRawSku>();
}
