package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {


    @GetMapping("/")
    public String init() {
        return "index";
    }

    @GetMapping("/Recipes")
    public String getRecipes(){return "recipe";}

    @GetMapping("/Recipe")
    public String getRecipe(){return "details";}

    @GetMapping("/LogIn")
    public String getLogin(){return "login";}

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
