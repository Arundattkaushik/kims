package com.kims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kims.dto.RawPartyDTO;
import java.util.List;
import com.kims.entites.Party;
import com.kims.entites.RawSku;



public interface RawPartyRepo extends JpaRepository<RawPartyDTO, Long> {

	public List<RawPartyDTO> findByParty(Party party);
	
	public List<RawPartyDTO> findByRawSku(RawSku rawSku);
}
