package com.example.demo.repository;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    Optional<Menu> findMenuById(Long id);
}
