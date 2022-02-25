package com.example.demo.controller;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.service.DatabaseInit;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
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

    @Autowired
    private MenuService menuService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private DatabaseInit dataService;


    @ModelAttribute
    public void addAttributes(Model model) {
        if (currentUser==null)
            model.addAttribute("loggedUser", false);
        else if (currentUser.getAdmin()) {
            model.addAttribute("admin", true);
            model.addAttribute("logged", true);
        }
        else {
            model.addAttribute("loggedUser", true);
            model.addAttribute("logged", true);
        }
    }



    @GetMapping("/")
    public String init(Model model) {
        Optional<Recipe> outstandingRecipes = recipeService.findById(20);
        Optional<Recipe> outstandingRecipes1 = recipeService.findById(22);
        List<Recipe> outstandingRecipesAux = recipeRepository.findAll();
        int contRecipes = 0;
        for(Recipe recipe: outstandingRecipesAux){
            contRecipes = contRecipes + 1;
        }
        Optional<Recipe> outstandingRecipes2 = recipeService.findById((long)(Math.random() * contRecipes));

        Page<Recipe> recipeCarrousel1 = recipeRepository.findAll(PageRequest.of(0,3,Sort.by("id").descending()));
        Page<Recipe> recipeCarrousel2 = recipeRepository.findAll(PageRequest.of(1,3,Sort.by("id").descending()));

        model.addAttribute("recipe1", outstandingRecipes.get());
        model.addAttribute("recipe2", outstandingRecipes1.get());
        model.addAttribute("recipeWeekly", outstandingRecipes2.get());
        model.addAttribute("recipeCarrousel1", recipeCarrousel1.getContent());
        model.addAttribute("recipeCarrousel2", recipeCarrousel2.getContent());
        return "index";
    }

    @GetMapping("/inicio")
    public String bbdd(Model model) throws IOException, URISyntaxException {
        dataService.init();
        return "index";
    }

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
    public String getAdminProfile(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("usersAll", users.size());

        return "Admin";}

    @GetMapping("/RecipeMaker")
    public String getRecipeMaker(Model model){return "RecipeMaker";}

    @GetMapping("/Recipe/{id}")
    public String showRecipe(Model model, @PathVariable long id) {
        if(currentUser != null) {
            if (currentUser.getStoredRecipes().stream().anyMatch(r -> r.getId() == id)) {
                model.addAttribute("disable", true);
            } else if (currentUser.getAdmin()) {
                model.addAttribute("adminDelete", true);
            } else {
                model.addAttribute("userRecipe", currentUser != null);
            }
        }
        Optional<Recipe> recipe = recipeService.findById(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            return "recipeLoaded";
        } else {
            return "recipe";
        }

    }

    @GetMapping("/Recipes")
    public String getAllRecipes(Model model){
        Page<Recipe> recipes = recipeRepository.findAll(PageRequest.of(0,12,Sort.by("id").descending()));
        List<Recipe> recipesModels = new ArrayList<>();
        for (Recipe recipe: recipes){
            recipesModels.add(recipe);
        }
        model.addAttribute("recipe", recipes.getContent());

        return "recipe";
    }

    @PostMapping("/processRemoveRecipe")
    public ModelAndView processRemoveRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().remove(recipe);
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/StoredRecipes", true));
    }

    @PostMapping("/processDeleteRecipe")
    public ModelAndView processDeleteRecipe(Model model, @RequestParam String id_RecipeDelete){
        long id=Long.parseLong(id_RecipeDelete);
        recipeService.delete(id);

        return new ModelAndView(new RedirectView("/Recipes", true));
    }

    @PostMapping("/processAddRecipe")
    public ModelAndView processAddRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().add(recipe.get());
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/Recipe/"+id_Recipe, true));
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

        User user = new User(mail, name, password, recipes, menuVoid, dietas, false);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userService.save(user);
            if(!user.getAdmin()) {
                model.addAttribute("loggedUser", true);
                model.addAttribute("logged",true);
            }else if(user.getAdmin()){
                model.addAttribute("admin", true);
                model.addAttribute("logged",true);
            }
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
        model.addAttribute("loggedUser",false);
        model.addAttribute("logged",false);
        model.addAttribute("admin", false);
        return "index";
    }

    @PostMapping("/processFormLogIn")
    public String processForm(Model model, @RequestParam String name,@RequestParam String password){
        Optional<User> tryUser = userService.findByName(name);
        if (tryUser.isPresent()) {
            if (tryUser.get().getPassword().equals(password)) {
                currentUser = tryUser.get();
                if(!currentUser.getAdmin()) {
                    model.addAttribute("loggedUser", true);
                    model.addAttribute("logged",true);
                }else if(currentUser.getAdmin()){
                    model.addAttribute("admin", true);
                    model.addAttribute("logged",true);
                }
                return "index";
            }
            else
                return "loginerror";
        }
        else
            return "loginerror";
    }

    @GetMapping("/StoredRecipes")
    public String getAllYourRecipes(Model model){
        List<Recipe> recipes = currentUser.getStoredRecipes();
        model.addAttribute("yourRecipe", recipes);

        return "Stored_Recipes";
    }

    @GetMapping("/YourMenu")
    public String getMenu_Activo(Model model){

        List<Recipe> lunches = new ArrayList<>();
        List<Recipe> dinners = new ArrayList<>();
        Optional<Menu> tryMenu = menuService.findById(currentUser.getActiveMenu().getId());
        if(tryMenu.isPresent()){
            lunches = tryMenu.get().getLunchs();
            dinners = tryMenu.get().getDinners();
            model.addAttribute("menu",tryMenu.get());
        }

        model.addAttribute("lunchs",lunches);
        model.addAttribute("dinners",dinners);
        return "Menu_Activo";}

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
