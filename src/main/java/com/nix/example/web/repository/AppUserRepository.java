package com.nix.example.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nix.example.web.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	public Optional<AppUser> findByEmail(String email);
}
