package com.example.demo.service;

import com.example.demo.model.Diet;
import com.example.demo.repository.DietRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DietService {
    private final DietRepository repository;

    public DietService(DietRepository repository) {
        this.repository = repository;
    }

    public Optional<Diet> findById(long id) {
        return repository.findDietById(id);
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

    public List<Diet> findAll(){return repository.findAll();}

    public Page<Diet> findAllDiet(Pageable page){return repository.findAll(page);}

    public Page<Diet> findAll(PageRequest pageRequest){return repository.findAll(pageRequest.of(0, 12, Sort.by("id").descending()));}


}
