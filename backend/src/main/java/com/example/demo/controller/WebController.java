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
}