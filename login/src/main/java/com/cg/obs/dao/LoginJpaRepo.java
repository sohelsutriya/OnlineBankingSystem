package com.cg.obs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.obs.model.LoginCredentials;

public interface LoginJpaRepo extends JpaRepository<LoginCredentials, Integer> {
	public Optional<LoginCredentials> findByUsername(String username);
}
