/**
 * 
 */
package com.cg.obs.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.obs.model.AuthenticationRequest;
import com.cg.obs.model.AuthenticationResponse;
import com.cg.obs.model.LoginCredentials;
import com.cg.obs.service.LoginService;
import com.cg.obs.service.MyUserDetailsService;

/**
 * @author sohel
 *
 */
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	LoginService service;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest req)
			throws Exception {

		try {
			// below is an example of manually triggering authentication with credentials
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),
					req.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		// if authentication succeeds, then for that username extract details
		final LoginCredentials credentials = service.getUserByUsername(req.getUsername()).get();

		// using the jwt utils library to generate jwt
		final String jwt = jwtTokenUtil.generateToken(credentials.getUsername(),credentials.getRole());

		// returning jwt as json
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
