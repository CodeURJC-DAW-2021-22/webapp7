package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {


    @GetMapping("/")
    public String init() {
        return "index.html";
    }

    @GetMapping("/Recipes")
    public String getRecipes(){return "recipe.html";}

    @GetMapping("/Recipe")
    public String getRecipe(){return "details.html";}

    @GetMapping("/LogIn")
    public String getLogin(){return "login.html";}

    @GetMapping("/YourMenu")
    public String getMenu_Activo(){return "Menu_Activo.html";}

    @GetMapping("/StoredRecipes")
    public String getStored_Recipes(){return "Stored_Recipes.html";}

    @GetMapping("/User")
    public String getUser(){return "user.html";}

    @GetMapping("/AboutUs")
    public String getAboutUs(){return "AboutUs.html";}

    @GetMapping("/StoredDiets")
    public String getStoredDiets(){return "DropDown.html";}

    @GetMapping("/AdminProfile")
    public String getAdminProfile(){return "Admin.html";}
}
