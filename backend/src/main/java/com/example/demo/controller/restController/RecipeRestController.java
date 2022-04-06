package com.example.demo.controller.restController;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/Recipes")
public class RecipeRestController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {

        Optional<Recipe> recipeId = recipeService.findById(id);
        if (recipeId.isPresent()) {
            Recipe recipe = recipeId.get();
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public Collection<Recipe> getAllRecipes(HttpServletRequest request) {
        return recipeService.findAll();
    }

    /*@GetMapping("/")
    public @ResponseBody
    Page<Recipe> getMoreRecipes(int page){
        if (page <= (int) Math.ceil(recipeService.count()/12)) {
            return recipeService.findAll(PageRequest.of(page,12));
        } else if ((int) (recipeService.count() % (12 * page)) > 0){
            int resto = (int) (recipeService.count() % (12 * page));
            return recipeService.findAll(PageRequest.of(page,resto));
        } else {
            return null;
        }
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> processDeleteRecipe(@RequestParam String id_RecipeDelete){
        long id=Long.parseLong(id_RecipeDelete);
        long longIDAux1 = 1;
        long longIDAux2 = 2;

        Optional<Recipe> recipeOptions = recipeService.findById(id);

        if(recipeOptions.isPresent()) {
            Recipe toRemove = recipeOptions.get();

            Recipe toChange;
            if (id == longIDAux1)
                toChange = recipeService.findById(longIDAux2).get();
            else
                toChange = recipeService.findById(longIDAux1).get();

            menuService.updateBeforeDelete(toChange.getId(), toRemove.getId());

            List<User> userList = userService.findAll();
            for (User u : userList) {
                User uLoaded = userService.findById(u.getId()).get();
                List<Recipe> recipeList = uLoaded.getStoredRecipes();
                if (recipeList.contains(toRemove)) {
                    uLoaded.removeStoredRecipes(toRemove.getId());
                }
                u = uLoaded;
                userService.save(u);
            }

            recipeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Recipe> createRecipe(HttpServletRequest request, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());

        if (currentUserOptional.isPresent()) {
            User currentUser = currentUserOptional.get();
            String creator = currentUser.getUsername();
            Date date = new Date();
            boolean vegetables = booleanos.contains("vegetables");
            boolean protein = booleanos.contains("protein");
            boolean hydrates = booleanos.contains("hydrates");
            boolean carbohydrates = booleanos.contains("carbohydrates");
            boolean highinfat = booleanos.contains("highinfat");
            Recipe recipe = new Recipe(name, time, difficulty, date, ingredients, creator, vegetables, protein, hydrates, carbohydrates, highinfat, preparation);
            recipeService.save(recipe);
            if (imageRecipe != null) {
                uploadImage(recipe.getId(), imageRecipe);
            }
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable long id, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
        Optional<Recipe> recipeOpt = recipeService.findById(id);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            boolean vegetables = booleanos.contains("vegetables");
            boolean protein = booleanos.contains("protein");
            boolean hydrates = booleanos.contains("hydrates");
            boolean carbohydrates = booleanos.contains("carbohydrates");
            boolean highinfat = booleanos.contains("highinfat");

            recipe.setName(name);
            recipe.setCookTime(time);
            recipe.setDifficulty(difficulty);
            recipe.setPreparation(preparation);
            recipe.setIngredients(ingredients);

            recipe.setVegetables(vegetables);
            recipe.setProtein(protein);
            recipe.setHydrates(hydrates);
            recipe.setCarbohydrates(carbohydrates);
            recipe.setHighinfat(highinfat);

            recipeService.save(recipe);
            if (imageRecipe.getSize() != 0) {
                uploadImage(recipe.getId(), imageRecipe);
            }
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageRecipe) throws IOException {
        Recipe recipe = recipeService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();
        recipe.setHasPhoto(true);
        recipe.setRecipeImage(BlobProxy.generateProxy(imageRecipe.getInputStream(), imageRecipe.getSize()));

        recipeService.save(recipe);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/image")
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
