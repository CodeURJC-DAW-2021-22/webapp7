package com.example.demo.controller;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
<<<<<<< HEAD
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
=======
import com.example.demo.model.User;
import com.example.demo.repository.MenuRepository;
>>>>>>> main
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
=======
import java.util.ArrayList;
>>>>>>> main
import java.util.List;
import java.util.Optional;


@Controller
public class WebController {


    private User currentUser=null;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
<<<<<<< HEAD
    private RecipeRepository recipeRepository;

    @Autowired
=======
>>>>>>> main
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

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

    @GetMapping("/RecipeMaker")
    public String getRecipeMaker(Model model){return "RecipeMaker";}

<<<<<<< HEAD
    @PostMapping("/processFormRecipe")
    public String processRecipeMaker(Model model, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam boolean vegetables, @RequestParam boolean protein, @RequestParam boolean hydrates, @RequestParam boolean carbohydrates, @RequestParam boolean highinfat){
        String creator = "creator";

        Date date = new Date();

        Recipe recipe = new Recipe(name, time, difficulty, date, creator, ingredients, vegetables, protein, hydrates, carbohydrates, highinfat, preparation);

        recipeRepository.save(recipe);
        return "Admin";
    }

    @PostMapping("/processFormSignUp")
    public String processRegister(Model model, @RequestParam String name, @RequestParam String password, @RequestParam String mail){
=======

    @PostMapping("/processFormSignUp")
    public String procesarRegistro(Model model, @RequestParam String name, @RequestParam String password, @RequestParam String mail){
>>>>>>> main
        Menu menuVoid = new Menu();
        List<Diet> dietas = new ArrayList<Diet>();
        menuRepository.save(menuVoid);

        User user = new User(mail, name, password, menuVoid, dietas);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userRepository.save(user);
            return "logIn";
        }
        else {
            return "loginerror";
        }
    }

    @PostMapping("/processFormLogIn")
<<<<<<< HEAD
    public String processForm(Model model, @RequestParam String name,@RequestParam String password){
=======
    public String procesarFormulario(Model model, @RequestParam String name,@RequestParam String password){
>>>>>>> main
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
