package com.devteamup.repository;

import com.devteamup.*;
import com.devteamup.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
