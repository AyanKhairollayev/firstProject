package com.web.simplewebapplication.repository;

import com.web.simplewebapplication.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByName(String name);
}