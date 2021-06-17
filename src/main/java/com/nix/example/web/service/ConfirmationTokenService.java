package com.nix.example.web.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nix.example.web.model.ConfirmationToken;
import com.nix.example.web.repository.ConfirmationTokenRepository;

@Service
public class ConfirmationTokenService {

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}
	
	public Optional<ConfirmationToken> getConfirmationToken(String token) {
		return confirmationTokenRepository.findByToken(token);
	}
	
	public int setConfirmedAt(String token) {		
		return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
	}
	
}
