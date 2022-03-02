package com.example.demo.service;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    private final DietRepository repository;

    public DietService(DietRepository repository) {
        this.repository = repository;
    }

    public Optional<Diet> findById(long id) {
        return repository.findById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public void save(Diet diet) {
        repository.save(diet);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
