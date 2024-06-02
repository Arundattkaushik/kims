package com.kims.entites;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * doc-> date of creation
 */

@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String partyName;
	private String mobile;
	private String email;
	private String address;
	private Date doc;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDoc() {
		return doc;
	}
	public void setDoc(Date doc) {
			this.doc = doc;
	}

	public Party() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Party [id=" + id + ", fName=" + partyName + ", mobile=" + mobile + ", email=" + email
				+ ", address=" + address + ", doc=" + doc + "]";
	}
}
