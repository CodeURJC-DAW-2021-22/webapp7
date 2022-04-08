package com.example.demo.controller.restController;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.DietService;
import com.example.demo.service.MenuService;
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
@RequestMapping("/api/diets")
public class DietRestController {

    @Autowired
    private DietService dietService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private MenuService menuService;

    private List<Menu> dietCreate = new ArrayList<>();


    @GetMapping("/{id}")
    public ResponseEntity<Diet> getDietPage(@PathVariable long id){

        Optional<Diet> dietId = dietService.findById(id);
        if (dietId.isPresent()) {
            Diet diet = dietId.get();
            return new ResponseEntity<>(diet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Page<Diet>> getMenu_All(HttpServletRequest request, Pageable page){
        Principal principal = request.getUserPrincipal();
        if (principal != null){
            Page<Diet> dietPage = dietService.findAllDiet(page);
            return new ResponseEntity<>(dietPage, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> processRemoveDiet(@PathVariable long id){return null;}

    @PostMapping("/new")
    public ResponseEntity<Diet> processFormDiet(HttpServletRequest request,@RequestParam String name){
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User currentUser = userService.findByName(principal.getName()).orElseThrow();
            Diet dietNew = new Diet(name, dietCreate);
            currentUser.addStoredDiets(dietNew);
            dietService.save(dietNew);
            userService.save(currentUser);
            dietCreate.removeAll(dietCreate);
        return new ResponseEntity<>(dietNew,HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> processAddDiet(HttpServletRequest request, @PathVariable long id){
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
                Optional<Menu> menuId = menuService.findById(id);
                if (menuId.isPresent()) {
                    Menu menu = menuId.get();
                    dietCreate.add(menu);
                    return new ResponseEntity<>(menu, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
