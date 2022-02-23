package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Optional<Menu> findById(long id) {
        return repository.findMenuById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);}

    public void save(Menu menu){
        repository.save(menu);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
