package com.cg.obs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.obs.dao.LoginJpaRepo;
import com.cg.obs.model.LoginCredentials;

@Service
public class LoginService {
	
	@Autowired
	private LoginJpaRepo lrepo;
	
	public LoginCredentials addLoginCredentials(LoginCredentials lc) {
		return lrepo.save(lc);
	}
	
	public List<LoginCredentials> getAllLoginCredentials(){
		return lrepo.findAll();
	}
	public Optional<LoginCredentials> getUserByUsername(String username){
		return lrepo.findByUsername(username);
	}

}
