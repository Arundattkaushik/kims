package com.kims.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kims.repositories.CompanyDetailsRepo;
import com.kims.services.MasterSkuServices;
import com.kims.services.PartyServices;
import com.kims.services.RawSkuServices;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionHandler {
	@Autowired
	private PartyServices partyServices;
	@Autowired
	private RawSkuServices rawSkuServices;
	@Autowired
	private MasterSkuServices mSkuServices;
	@Autowired
	private CompanyDetailsRepo companyDetailService;

	public static void resetSession(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("partyList");
		session.removeAttribute("rSkuList");
	}
	
	public void sessionLoader(HttpSession session) {
		session.setAttribute("partyList", partyServices.getParties());
		session.setAttribute("rSkuList", rawSkuServices.getRawSkuList());
		session.setAttribute("mSkuList", mSkuServices.getAllMasterSkus());
		session.setAttribute("compDetails", companyDetailService.getCompanyDetails());
	}
	
	
	
}
