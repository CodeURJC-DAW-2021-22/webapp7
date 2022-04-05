package com.example.demo.controller.RestController;

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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/User")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(HttpServletRequest request){ return null;}

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> processRegister(@RequestParam String name, @RequestParam String password, @RequestParam String mail){
        Menu menu = menuService.findAll().get(1);
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();

        User user = new User(mail, name, passwordEncoder.encode(password), recipes, menu, dietas, false);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Recipes")
    public Collection<Recipe> getAllYourRecipes(HttpServletRequest request){return null;}

    @GetMapping("/Menu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request){return null;}

    @PostMapping("/Menus/{id}")
    public ResponseEntity<Menu> processActiveMenu(@RequestParam String id_Menu){return null;}
}
