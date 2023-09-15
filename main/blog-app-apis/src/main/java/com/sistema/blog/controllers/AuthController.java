package com.sistema.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.exceptions.ApiException;
import com.sistema.blog.payloads.JwtAuthRequest;
import com.sistema.blog.payloads.JwtAuthResponse;
import com.sistema.blog.payloads.UserDto;
import com.sistema.blog.security.JwtTokenHelper;
import com.sistema.blog.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {

		this.authenticate(request.getUsername(), request.getPassword());

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUsername(userDetails.getUsername());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
				password);
		try {
			authenticationManager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new ApiException(" username y password invalidos !!");
		}

	}

	@PostMapping(value = "/singup")
	public ResponseEntity<UserDto> singUp(@RequestBody UserDto userDto) {

		UserDto registerNewUser = this.userService.registerNewUser(userDto);

		return new ResponseEntity<>(registerNewUser, HttpStatus.CREATED);
	}
}
