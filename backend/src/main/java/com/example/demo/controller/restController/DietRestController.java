package com.example.demo.controller.restController;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/diets")
public class DietRestController {

    @Autowired
    private DietService dietService;

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
    public Collection<Diet> getStoredDiets(HttpServletRequest request){return null;}

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> processRemoveDiet(@PathVariable long id){return null;}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Diet processFormDiet(@RequestParam String name){return null;}

}
