package com.example.demo.controller;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class WebController {


    private User currentUser=null;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

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
    @GetMapping("/SingUpError")
    public String getSignUpError(Model model){
        return "singuperror";
    }

    @GetMapping("/YourMenu")
    public String getMenu_Activo(Model model){return "Menu_Activo";}

    @GetMapping("/StoredRecipes")
    public String getStored_Recipes(Model model){return "Stored_Recipes";}

    @GetMapping("/User")
    public String getUser(Model model){
        model.addAttribute("username",currentUser.getUsername());
        model.addAttribute("mail",currentUser.getMail());
        model.addAttribute("password",currentUser.getPassword());
        return "user";}

    @GetMapping("/AboutUs")
    public String getAboutUs(Model model){return "AboutUs";}

    @GetMapping("/StoredDiets")
    public String getStoredDiets(Model model){return "DropDown";}

    @GetMapping("/AdminProfile")
    public String getAdminProfile(Model model){return "Admin";}

    @GetMapping("/RecipeMaker")
    public String getRecipeMaker(Model model){return "RecipeMaker";}

    @GetMapping("/Recipe/{id}")
    public String showRecipe(Model model, @PathVariable long id) {

        if(currentUser != null && currentUser.getStoredRecipes().stream().anyMatch(r -> r.getId() == id)){
            model.addAttribute("disable", true);
        }else{
            model.addAttribute("user", currentUser != null);
        }

        Optional<Recipe> recipe = recipeService.findById(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            return "recipeLoaded";
        } else {
            return "recipe";
        }

    }

    @PostMapping("/processAddRecipe")
    public String processAddRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().add(recipe.get());

        return "recipe";
    }

    @PostMapping("/processFormRecipe")
    public String processRecipeMaker(Model model, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam boolean vegetables, @RequestParam boolean protein, @RequestParam boolean hydrates, @RequestParam boolean carbohydrates, @RequestParam boolean highinfat){
        String creator = "creator";

        Date date = new Date();

        Recipe recipe = new Recipe(name, time, difficulty, date, creator, ingredients, vegetables, protein, hydrates, carbohydrates, highinfat, preparation);

        recipeService.save(recipe);
        return "Admin";
    }

    @PostMapping("/processFormSignUp")
    public String processRegister(Model model, @RequestParam String name, @RequestParam String password, @RequestParam String mail){
        Menu menuVoid = new Menu();
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();
        menuRepository.save(menuVoid);

        User user = new User(mail, name, password, recipes, menuVoid, dietas);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userService.save(user);
            model.addAttribute("logged",true);
            currentUser=user;
            return "index";
        }
        else {
            return "singuperror";
        }
    }
    @GetMapping("/processLogOut")
    public String LogOut(Model model){
        this.currentUser=null;
        model.addAttribute("logged",false);
        return "index";
    }

    @PostMapping("/processFormLogIn")
    public String processForm(Model model, @RequestParam String name,@RequestParam String password){
        Optional<User> tryUser = userService.findByName(name);
        if (tryUser.isPresent()) {
            if (tryUser.get().getPassword().equals(password)) {
                currentUser = tryUser.get();
                model.addAttribute("logged",true);
                return "index";
            }
            else
                return "loginerror";
        }
        else
            return "loginerror";
    }

    @GetMapping("/recipe/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

        Optional<Recipe> recipe = recipeService.findById(id);
        if (recipe.isPresent() && recipe.get().getRecipeImage() != null) {

            Resource file = new InputStreamResource(recipe.get().getRecipeImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
                    .contentLength(recipe.get().getRecipeImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
