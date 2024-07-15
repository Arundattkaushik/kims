package com.kims.entites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class MasterSku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String mSkuTitle;
	private String mSkuDesc;
	private String mHsn;
	
	@Temporal(TemporalType.DATE)
	private Date doc;
	
	@ElementCollection
	private List<Long> rSkuIds = new ArrayList<Long>();
	
	@ElementCollection
	private List<Integer> ordRawSkuQty = new ArrayList<Integer>();
	
	@PrePersist
    protected void onCreate() {
        this.doc = new Date();
    }
}
