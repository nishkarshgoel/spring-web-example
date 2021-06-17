package com.nix.example.web.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nix.example.web.model.AppUser;
import com.nix.example.web.model.ConfirmationToken;
import com.nix.example.web.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService{

	private final static String USER_NOT_FOUND = "user with email %s not found";
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username)));
	}
	
	public void enableAppUser(String username) {
		AppUser appUser = (AppUser) loadUserByUsername(username);
		appUser.setEnabled(true);
		appUserRepository.save(appUser);
	}

	public String signup(AppUser appUser) {
		boolean userexist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		
		if(userexist) {
			throw new IllegalStateException("email already taken");
		}
		
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		
		appUserRepository.save(appUser);
		
		String token = UUID.randomUUID().toString();
		
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15l),
				appUser
				);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		//TODO : Send Email
		
		return token;
	}
}
