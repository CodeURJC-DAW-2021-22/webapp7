package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

        public Optional<User> findById(long id) {
            return repository.findById(id);
        }
        public Optional<User> findByName(String name) {
            return repository.findByName(name);
        }
        public Optional<User> findByMail(String mail) {
        return repository.findByMail(mail);
    }

        public boolean exist(long id) {
            return repository.existsById(id);
        }

        public List<User> findAll() {
            return repository.findAll();
        }

        public void save(User book) {
            repository.save(book);
        }

        public void delete(long id) {
            repository.deleteById(id);
        }
}
