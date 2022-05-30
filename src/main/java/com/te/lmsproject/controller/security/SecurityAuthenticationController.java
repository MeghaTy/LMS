package com.te.lmsproject.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.lmsproject.service.security.MyUserDetialsService;
import com.te.lmsproject.springsecurity.jwtutil.JwtUtil;
import com.te.lmsproject.springsecurity.pojo.AuthenticationRequest;
import com.te.lmsproject.springsecurity.pojo.AuthenticationResponse;

@RestController
public class SecurityAuthenticationController {
	
	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private MyUserDetialsService detailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) {
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		} catch (BadCredentialsException e) {
			e.printStackTrace();
		}
		UserDetails userDetails = detailsService.loadUserByUsername(request.getUsername());

		String jwt = jwtUtil.generateToken(userDetails);

		return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
}
}