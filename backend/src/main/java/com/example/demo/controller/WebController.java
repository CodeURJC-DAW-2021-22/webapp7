package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class WebController {


    private User currentUser;

    @Autowired
    private UserService userService;




    @GetMapping("/")
    public String init() {
        return "index";
    }

    @GetMapping("/Recipes")
    public String getRecipes(){return "recipe";}

    @GetMapping("/Recipe")
    public String getRecipe(){return "details";}

    @GetMapping("/LogIn")
    public String getLogin(){
        return("login");
    }

    @PostMapping("/processFormLogIn")
    public String procesarFormulario(@RequestParam String name,@RequestParam String password){
        Optional<User> tryUser = userService.findByName(name);
        if (tryUser.isPresent()) {
            if (tryUser.get().getPassword().equals(password)) {
                currentUser = tryUser.get();
                return "index";
            }
            else
                return "loginerror";
        }
        else
            return "loginerror";
    }

    @GetMapping("/LogInError")
    public String getLoginError(){
        return "loginerror";
    }

    @GetMapping("/YourMenu")
    public String getMenu_Activo(){return "Menu_Activo";}

    @GetMapping("/StoredRecipes")
    public String getStored_Recipes(){return "Stored_Recipes";}

    @GetMapping("/User")
    public String getUser(){return "user";}

    @GetMapping("/AboutUs")
    public String getAboutUs(){return "AboutUs";}

    @GetMapping("/StoredDiets")
    public String getStoredDiets(){return "DropDown";}

    @GetMapping("/AdminProfile")
    public String getAdminProfile(){return "Admin";}
}
