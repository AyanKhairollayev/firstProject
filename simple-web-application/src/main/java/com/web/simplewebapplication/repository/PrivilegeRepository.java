package com.web.simplewebapplication.repository;

import com.web.simplewebapplication.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}