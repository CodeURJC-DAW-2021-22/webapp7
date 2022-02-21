package com.example.demo.service;


import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DietRepository dietRepository;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        //sample users
        Menu menuVoid= new Menu();
        menuRepository.save(menuVoid);
        List<Menu> menuList = new ArrayList<Menu>();
        List<Diet> dietas = new ArrayList<Diet>();
        User user0 = new User("user0@gmail.com","user0","123",menuVoid,dietas);
        User user1 = new User("user1@gmail.com","user1","123",menuVoid,dietas);
        User user2 = new User("user2@gmail.com","user2","123",menuVoid,dietas);
        User user3 = new User("user3@gmail.com","user3","123",menuVoid,dietas);
        User user4 = new User("user4@gmail.com","user4","123",menuVoid,dietas);
        User admin = new User("admin@gmail.com","admin","123",menuVoid,dietas);

        userRepository.save(user0);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(admin);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(18/07/2021);

        Recipe recipe0 = new Recipe("Tortilla de patatas", 30, "Intermedia", date, "5 patatas, 3 huevos y 1 cebolleta", "Rodri_Chef", false, true, true, false,false, "Cortar, batir, pochar y cuajar poco");
        Recipe recipe1 = new Recipe("Huevos revueltos", 5, "Fácil", date, "2 huevos", "Rodri_Chef", false, true, false, false,false, "A la sartén y remover");
        Recipe recipe2 = new Recipe("Ensalada de tomate", 7, "Fácil", date, "2 tomates, mozarella y especias al gusto", "Rodri_Chef", true, true, false, false, false,"Cortar los tomates y alinear al gusto junto con la mozarella");
    }
    public void setRecipeImage (Recipe newRecipe, String classpathResource) throws IOException {
        newRecipe.setHasPhoto(true);
        Resource image = new ClassPathResource(classpathResource);
        newRecipe.setRecipeImage(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
