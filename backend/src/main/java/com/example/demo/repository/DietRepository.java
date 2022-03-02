package com.example.demo.repository;

import com.example.demo.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DietRepository extends JpaRepository<Diet,Long> {

    Optional<Diet> findDietById(Long id);
}
