package com.kims.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public static void resetSession(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("partyList");
		session.removeAttribute("rawSkuList");
	}
	
	public void sessionLoader(HttpSession session) {
		session.setAttribute("partyList", partyServices.getParties());
		session.setAttribute("rawSkuList", rawSkuServices.getRawSkuList());
		session.setAttribute("mSkuList", mSkuServices.getAllMasterSkus());
	}
	
	
	
}
