package com.cg.obs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.obs.dao.LoginJpaRepo;
import com.cg.obs.model.LoginCredentials;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	LoginJpaRepo repo;

	@Autowired
	Optional<LoginCredentials> user;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = repo.findByUsername(username);

		// Just copy paste the next two lines
		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		roleList.add(new SimpleGrantedAuthority(user.get().getRole()));

		return new User(user.get().getUsername(), user.get().getPassword(), roleList);
	}

}
