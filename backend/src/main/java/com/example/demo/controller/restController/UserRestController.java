package com.example.demo.controller.restController;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @GetMapping("/me")
    public ResponseEntity<User> getProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return new ResponseEntity<>(user, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> processRegister(@RequestParam String name, @RequestParam String password, @RequestParam String mail) {
        Menu menu = menuService.findAll().get(1);
        List<Diet> dietas = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<Recipe>();

        User user = new User(mail, name, passwordEncoder.encode(password), recipes, menu, dietas, false);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> userPrincipal = userService.findById(id);
        if (userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recipes")
    public ResponseEntity<Collection<Recipe>> getAllYourRecipes(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            List<Recipe> listStoredRecipe = user.getStoredRecipes();
            return new ResponseEntity<>(listStoredRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/menu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            Optional<Menu> tryMenu = menuService.findById(user.getActiveMenu().getId());
            if (tryMenu.isPresent()) {
                Menu menu = tryMenu.get();
                return new ResponseEntity<>(menu, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/diets")
    public ResponseEntity<Collection<Diet>> getStoredDiets(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            List<Diet> trydiet = user.getStoredDiets();
                return new ResponseEntity<>(trydiet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
