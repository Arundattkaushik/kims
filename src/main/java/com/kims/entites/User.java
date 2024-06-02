package com.kims.entites;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fName;
	private String lName;
	private String gender;
	private String dob;
	
	private String mobile;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String address;
	
	private String doj;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		if (dob.equals("null")) {
			this.dob = new Date().toString();
		} else {
			this.dob = dob;
		}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		if (doj == null) {
			this.doj = new Date().toString();
		} 
		else {
			this.doj = doj;
		}
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", gender=" + gender + ", dob=" + dob
				+ ", mobile=" + mobile + ", email=" + email + ", password=" + password + ", address=" + address
				+ ", doj=" + doj+ "]";
	}
}
