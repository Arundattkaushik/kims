package com.kims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.kims.repositories.CompanyDetailsRepo;
import com.kims.repositories.MasterSkuRepository;
import com.kims.repositories.PartyRepository;
import com.kims.repositories.RawSkuRepository;
import com.kims.repositories.SalesRepo;
import com.kims.repositories.SoldRawSkuRepo;

@SpringBootApplication
public class KimSApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(KimSApplication.class, args);
		context.getBean(PartyRepository.class);
		context.getBean(RawSkuRepository.class);
		context.getBean(MasterSkuRepository.class);
		context.getBean(CompanyDetailsRepo.class);
		context.getBean(SalesRepo.class);
		context.getBean(SoldRawSkuRepo.class);
		
	}

}
