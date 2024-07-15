package com.kims.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PurchaseInvoice {
	@Id
	 private Long id;
}
