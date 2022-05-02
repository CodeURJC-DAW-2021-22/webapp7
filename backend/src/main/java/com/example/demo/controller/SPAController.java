package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SPAController {
    @GetMapping({"/new/**/{path:[^\\.]*}", "/{path:new[^\\.]*}"})
    public String redirect() {
        return "forward:/new/index.html";
    }
}

//este controlador no ejecutaba bien con la versión de las transparencias asi que hemos borrado la primera parte del mapping para que no de error.
//hay que probarlo cuando tengamos avanzada la parte de angular
