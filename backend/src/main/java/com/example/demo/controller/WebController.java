package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@Controller
public class WebController {


    private User currentUser=null;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model) {
        if (currentUser==null)
            model.addAttribute("logged", false);
        else
            model.addAttribute("logged",true);
    }



    @GetMapping("/")
    public String init(Model model) {
        return "index";
    }

    @GetMapping("/Recipes")
    public String getRecipes(Model model){return "recipe";}

    @GetMapping("/Recipe")
    public String getRecipe(Model model){return "details";}

    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return("login");
    }

    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "loginerror";
    }

    @GetMapping("/YourMenu")
    public String getMenu_Activo(Model model){return "Menu_Activo";}

    @GetMapping("/StoredRecipes")
    public String getStored_Recipes(Model model){return "Stored_Recipes";}

    @GetMapping("/User")
    public String getUser(Model model){return "user";}

    @GetMapping("/AboutUs")
    public String getAboutUs(Model model){return "AboutUs";}

    @GetMapping("/StoredDiets")
    public String getStoredDiets(Model model){return "DropDown";}

    @GetMapping("/AdminProfile")
    public String getAdminProfile(Model model){return "Admin";}

    @PostMapping("/processFormLogIn")
    public String procesarFormulario(Model model, @RequestParam String name,@RequestParam String password){
        Optional<User> tryUser = userService.findByName(name);
        if (tryUser.isPresent()) {
            if (tryUser.get().getPassword().equals(password)) {
                currentUser = tryUser.get();
                //model.addAttribute("logged",true);
                return "index";
            }
            else
                return "loginerror";
        }
        else
            return "loginerror";
    }
}
