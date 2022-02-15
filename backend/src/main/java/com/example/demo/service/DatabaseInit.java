package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class DatabaseInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private DietRepository dietRepository;

    //@PostConstruct
    public void init() throws IOException, URISyntaxException{

        //sample users

        userRepository.save(new User("user0@gmail.com","user0","123"));
        userRepository.save(new User("user1@gmail.com","user1","123"));
        userRepository.save(new User("user2@gmail.com","user2","123"));
        userRepository.save(new User("user3@gmail.com","user3","123"));
        userRepository.save(new User("user4@gmail.com","user4","123"));
        userRepository.save(new User("admin@gmail.com","admin","123"));
    }
}
