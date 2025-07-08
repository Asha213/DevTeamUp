package com.devteamup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteamup.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
