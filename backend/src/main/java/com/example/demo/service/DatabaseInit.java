package com.example.demo.service;


import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
        Menu menuVoid = new Menu();
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
        Date date = new Date();

        Recipe recipe0 = new Recipe("Tortilla de patatas", 30, "Intermedia", formatter.format(date), "5 patatas, 3 huevos y 1 cebolleta", "Rodri_Chef", false, true, true, false, "Cortar, batir, pochar y cuajar poco");
        Recipe recipe1 = new Recipe("Huevos revueltos", 5, "Fácil", formatter.format(date), "2 huevos", "Rodri_Chef", false, true, false, false, "A la sartén y remover");
        Recipe recipe2 = new Recipe("Ensalada de tomate", 7, "Fácil", formatter.format(date), "2 tomates, mozarella y especias al gusto", "Rodri_Chef", true, true, false, false, "Cortar los tomates y alinear al gusto junto con la mozarella");

        RecipesList listReceips1 = ArrayList<Recipe>;
        listReceips1 = = (recipe0, recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8,recipe9, recipe10, recipe11, recipe12, recipe0);
        Menu menu0 = new Menu(listReceips1);
    }
}
