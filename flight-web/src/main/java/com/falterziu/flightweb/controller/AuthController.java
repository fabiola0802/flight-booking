package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.security.AuthenticationService;
import com.falterziu.flightdata.security.LoginRequest;
import com.falterziu.flightdata.security.LogoutRequest;
import com.falterziu.flightdata.security.jwt.JwtProvider;
import com.falterziu.flightdata.util.Routes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

public class AuthController {


	private final AuthenticationManager authenticationManager;
	private  final AuthenticationService authenticationService;
	private final JwtProvider jwtProvider;

	public AuthController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.authenticationService = authenticationService;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping(value = Routes.LOGIN)
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		return ResponseEntity.ok(authenticationService.createResponse(authentication));
	}


	@PostMapping(Routes.LOGOUT)
	public ResponseEntity<Void> logout(@Valid LogoutRequest logoutRequest){
        jwtProvider.invalidateJwtToken(logoutRequest.getToken());
		return ResponseEntity.ok().build();
	}

}