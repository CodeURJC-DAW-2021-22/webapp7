package com.example.demo.controller.restController;

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
import java.io.IOException;
import java.util.Collection;
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


    /*@PostMapping("/")
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
    }*/

    @PostMapping("/")
    public ResponseEntity<User> Register(@RequestBody User user) throws IOException {
        if (user.getName().isBlank() || userService.findByName(user.getName()).isPresent()){
            /*user.setName(user.getName());
            user.setMail(user.getMail());
            user.setPassword(user.getPassword());*/
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            User newUser = new User(user.getMail(), user.getName(), user.getPassword(),user.getStoredRecipes(), user.getActiveMenu(), user.getStoredDiets(), user.getAdmin() );
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        Optional<User> userPrincipal = userService.findById(id);
        if (userPrincipal.isPresent()){
            User user = userPrincipal.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recipes")
    public Collection<Recipe> getAllYourRecipes(HttpServletRequest request){return null;}

    @GetMapping("/menu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request){return null;}

    @PostMapping("/menus/{id}")
    public ResponseEntity<Menu> processActiveMenu(@RequestParam String id_Menu){return null;}
}
