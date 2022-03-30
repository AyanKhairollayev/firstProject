package com.web.simplewebapplication.repository;

import com.web.simplewebapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    Optional<User> getByName(String name);
}