package com.example.demo.controller;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RecipeService recipeService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal == null)
            model.addAttribute("logged", false);
        else {
            model.addAttribute("logged", true);
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
        }
    }

    @GetMapping("/User")
    public String getUser(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        model.addAttribute("username",currentUser.getUsername());
        model.addAttribute("mail",currentUser.getMail());
        model.addAttribute("password",currentUser.getPassword());
        return "user";
    }

    @GetMapping("/AdminProfile")
    public String getAdminProfile(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        List<User> users = userService.findAll();
        model.addAttribute("usersAll", users.size());
        model.addAttribute("adminName", currentUser.getName());

        int healthyUsers=0;
        int unhealthyUsers=0;
        for (User u : users){
            Optional<Menu> tryMenu = menuService.findById(u.getActiveMenu().getId());
            if (tryMenu.isPresent()){
                Menu menu = tryMenu.get();
                if (menu.isHealthy())
                    healthyUsers++;
                else
                    unhealthyUsers++;
            }
        }
        model.addAttribute("numberOfHealthyUsers", healthyUsers);
        model.addAttribute("numberOfUnhealthyUsers",unhealthyUsers);
        return "Admin";
    }

    @PostMapping("/processFormSignUp")
    public ModelAndView processRegister(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam String password, @RequestParam String mail){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        Menu menu = menuService.findAll().get(1);
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();

        User user = new User(mail, name, passwordEncoder.encode(password), recipes, menu, dietas, false);

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
            return new ModelAndView(new RedirectView("/", true));
        }
        else {
            return new ModelAndView(new RedirectView("/SingUpError", true));
        }
    }

    @GetMapping("/SingUpError")
    public String getSignUpError(Model model){
        return "singuperror";
    }

    @PostMapping("/processAddRecipe")
    public ModelAndView processAddRecipe(Model model, HttpServletRequest request, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().add(recipe.get());
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/Recipe/"+id_Recipe, true));
    }

    @GetMapping("/StoredRecipes")
    public String getAllYourRecipes(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        List<Recipe> recipes = currentUser.getStoredRecipes();
        model.addAttribute("yourRecipe", recipes);

        return "Stored_Recipes";
    }

    @GetMapping("/StoredDiets")
    public String getStoredDiets(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        List<Diet> dietas = currentUser.getStoredDiets();
        model.addAttribute("diets",dietas);
        return "DropDown";
    }

    @PostMapping("/processActiveMenu")
    public ModelAndView processActiveMenu(Model model, HttpServletRequest request, @RequestParam String id_Menu){
        long id=Long.parseLong(id_Menu);

        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        Optional<Menu> menu = menuService.findById(id);
        currentUser.setActiveMenu(menu.get());
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/YourMenu", true));
    }

    @GetMapping("/YourMenu")
    public String getMenu_Activo(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        List<Recipe> lunches = new ArrayList<>();
        List<Recipe> dinners = new ArrayList<>();

        Optional<Menu> tryMenu = menuService.findById(currentUser.getActiveMenu().getId());
        if(tryMenu.isPresent()){
            lunches = tryMenu.get().getLunchs();
            dinners = tryMenu.get().getDinners();
            model.addAttribute("menu",tryMenu.get());

            int[] scoreArray = new int[5];

            Optional<Menu> tryMenuForScore = menuService.findById(tryMenu.get().getId());

            if (tryMenuForScore.isPresent()) {
                int cont=0;
                for (int i :tryMenuForScore.get().getMenuScore()){
                    scoreArray[cont] = i;
                    cont++;
                }
            }

            model.addAttribute("vegetablesStandard",8);
            model.addAttribute("vegetablesMenu",scoreArray[0]);
            model.addAttribute("proteinStandard",5);
            model.addAttribute("proteinMenu",scoreArray[1]);
            model.addAttribute("hydratesStandard",3);
            model.addAttribute("hydratesMenu",scoreArray[2]);
            model.addAttribute("carboHydratesStandard",2);
            model.addAttribute("carboHydratesMenu",scoreArray[3]);
            model.addAttribute("highInFatStandard",1);
            model.addAttribute("highInFatMenu",scoreArray[4]);
            model.addAttribute("IsMenuHealthy",tryMenuForScore.get().isHealthy());
            model.addAttribute("menuScore",tryMenuForScore.get().getScore());
        }

        model.addAttribute("lunchs",lunches);
        model.addAttribute("dinners",dinners);
        return "ActiveMenu";}
}
