package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Menu> findAll(){return repository.findAll();}

    public Page<Menu> findAllMenu(Pageable page){return repository.findAll(page);}

    public Page<Menu> findAll(PageRequest pageRequest){return repository.findAll(pageRequest.of(0, 12, Sort.by("id").descending()));}

    public void updateBeforeDelete(long idToChange, long idToRemove){repository.updateBeforeDelete(idToChange,idToRemove);}

    public void delete(long id) {
        repository.deleteById(id);
    }
}
