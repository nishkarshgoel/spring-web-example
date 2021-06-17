package com.nix.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nix.example.web.model.RegistrationRequest;
import com.nix.example.web.service.RegistrationService;

@RestController
@RequestMapping(path="api/v1/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
	@GetMapping("/confirm")
	public String confirmToken(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
}
