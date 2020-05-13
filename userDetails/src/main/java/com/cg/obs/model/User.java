/*
 * 
 * @Author: Simran
 */
package com.cg.obs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@Column
	private String fname;
	@Column
	private String lname;
	@Column
	private String mobile;
	@Column
	private String address;
	@Column
	private String panCard;
	@Column
	private String email;
	@Column
	private String occupation;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String fname, String lname, String mobile, String address, String panCard,
			String email, String occupation) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.address = address;
		this.panCard = panCard;
		this.email = email;
		this.occupation = occupation;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", mobile=" + mobile + ", address="
				+ address + ", password=" + ", panCard=" + panCard + ", email=" + email + ", occupation="
				+ occupation + "]";
	}

}
