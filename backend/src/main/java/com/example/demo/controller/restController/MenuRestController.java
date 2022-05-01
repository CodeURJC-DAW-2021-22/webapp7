package com.example.demo.controller.restController;

import com.example.demo.model.Menu;
import com.example.demo.model.Recipe;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RecipeService recipeService;


    @GetMapping("/")
    public ResponseEntity<List<Menu>> getMenu_All(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if (principal != null){
            List<Menu> menuPage = menuService.findAll();
            return new ResponseEntity<>(menuPage, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenu_Selected(@PathVariable long id){
        Optional<Menu> selectedMenu = menuService.findById(id);
        if(selectedMenu.isPresent()){
            Menu menu = selectedMenu.get();
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Menu> processRecipeMaker(@RequestParam String name, @RequestParam long lunchMonday, @RequestParam long lunchTuesday, @RequestParam long lunchWednesday, @RequestParam long lunchThursday, @RequestParam long lunchFriday, @RequestParam long lunchSaturday, @RequestParam long lunchSunday, @RequestParam long dinnerMonday, @RequestParam long dinnerTuesday, @RequestParam long dinnerWednesday, @RequestParam long dinnerThursday, @RequestParam long dinnerFriday, @RequestParam long dinnerSaturday, @RequestParam long dinnerSunday, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        if (principal != null) {

            List<Recipe> weekRecipes = new ArrayList<>();

            weekRecipes.add(recipeService.findById(lunchMonday).get());
            weekRecipes.add(recipeService.findById(dinnerMonday).get());

            weekRecipes.add(recipeService.findById(lunchTuesday).get());
            weekRecipes.add(recipeService.findById(dinnerTuesday).get());

            weekRecipes.add(recipeService.findById(lunchWednesday).get());
            weekRecipes.add(recipeService.findById(dinnerWednesday).get());

            weekRecipes.add(recipeService.findById(lunchThursday).get());
            weekRecipes.add(recipeService.findById(dinnerThursday).get());

            weekRecipes.add(recipeService.findById(lunchFriday).get());
            weekRecipes.add(recipeService.findById(dinnerFriday).get());

            weekRecipes.add(recipeService.findById(lunchSaturday).get());
            weekRecipes.add(recipeService.findById(dinnerSaturday).get());

            weekRecipes.add(recipeService.findById(lunchSunday).get());
            weekRecipes.add(recipeService.findById(dinnerSunday).get());

            Menu menu = new Menu(name, weekRecipes);
            menuService.save(menu);
            return new ResponseEntity<>(menu, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

