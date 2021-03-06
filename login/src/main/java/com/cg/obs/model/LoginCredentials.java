package com.cg.obs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class LoginCredentials {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String role;

	public LoginCredentials() {
		// TODO Auto-generated constructor stub
	}

	public LoginCredentials(int userId, String username, String password, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}