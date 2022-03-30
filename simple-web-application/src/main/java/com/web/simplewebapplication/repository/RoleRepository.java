package com.web.simplewebapplication.repository;

import com.web.simplewebapplication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String name);
}