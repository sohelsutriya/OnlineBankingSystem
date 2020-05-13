package com.cg.obs.util;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.obs.model.LoginCredentials;
import com.cg.obs.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Api
public class Control {

	@Autowired
	private LoginService service;

	private static final Logger logger = LoggerFactory.getLogger(Control.class);

	@PostMapping("/Login/add")
	@ApiOperation(value = "login", nickname = "login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = LoginCredentials.class),
			@ApiResponse(code = 500, message = "Failure", response = LoginCredentials.class) })
	public LoginCredentials login(@RequestBody LoginCredentials user) {

		List<LoginCredentials> list_lc = service.getAllLoginCredentials().stream()
				.filter(l -> l.getUsername().equals(user.getUsername())).collect(Collectors.toList());
		LoginCredentials lc;
		if (!list_lc.isEmpty()) {
			lc = list_lc.get(0);
			System.out.println(lc.getUsername() + lc.getUserId() + lc.getPassword());
			System.out.println(user.getUsername() + user.getUserId() + user.getPassword());
			if (lc.getPassword().equals(user.getPassword())) {
				return lc;
			} else
				return null;
		} else
			return null;

	}

	@PostMapping("/AddLogin")
	@ApiOperation(value = "addLoginDetails", nickname = "addLoginDetails")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = LoginCredentials.class),
			@ApiResponse(code = 500, message = "Failure", response = LoginCredentials.class) })
	public void addLoginDetails(@RequestBody LoginCredentials lc) {

		service.addLoginCredentials(lc);

	}

	@GetMapping("/getAllLoginCredentials")
	@ApiOperation(value = "getAllLoginCredentials", nickname = "getAllLoginCredentials")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = LoginCredentials.class),
			@ApiResponse(code = 500, message = "Failure", response = LoginCredentials.class) })
	public List<LoginCredentials> getAllLoginCredentials() {
		return service.getAllLoginCredentials();
	}
}
