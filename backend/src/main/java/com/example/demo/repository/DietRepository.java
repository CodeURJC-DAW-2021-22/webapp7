package com.example.demo.repository;

import com.example.demo.model.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet,Long> {
}
