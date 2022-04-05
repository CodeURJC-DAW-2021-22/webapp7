package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private MenuService menuService;

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

    @GetMapping("/Recipe/{id}")
    public String showRecipe(Model model, HttpServletRequest request, @PathVariable long id) {
        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            Optional<User> currentUserOptional = userService.findByName(principal.getName());
            User currentUser = currentUserOptional.get();
            if (currentUser.getAdmin()) {
                model.addAttribute("adminDelete", true);
                model.addAttribute("adminUpdate", true);
            } else if (currentUser.getStoredRecipes().stream().anyMatch(r -> r.getId() == id)) {
                model.addAttribute("disable", true);
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

    @GetMapping("/RecipeMaker")
    public String getRecipeMaker(Model model){return "RecipeMaker";}

    @GetMapping("/RecipeUpdater/{id}")
    public String getRecipeMaker(Model model, @PathVariable long id) throws SQLException {
        Optional<Recipe> recipe = recipeService.findById(id);
        if (recipe.isPresent()) {
            model.addAttribute("recipe", recipe.get());
            Recipe recipeModel = recipe.get();
            if(recipeModel.getDifficulty().equals("Intermedia")){
                model.addAttribute("middle", true);
                model.addAttribute("notHard", true);
            }else if(recipeModel.getDifficulty().equals("Posibilidad de lesión")){
                model.addAttribute("hard", true);
                model.addAttribute("notMiddle", true);
            }else{
                model.addAttribute("notMiddle", true);
                model.addAttribute("notHard", true);
            }

            if(recipeModel.getVegetables()){
                model.addAttribute("vegetables", "checked");
            }else{
                model.addAttribute("vegetables", "unchecked");
            }

            if(recipeModel.getProtein()){
                model.addAttribute("protein", "checked");
            }else{
                model.addAttribute("protein", "unchecked");
            }

            if(recipeModel.getHydrates()){
                model.addAttribute("hydrates", "checked");
            }else{
                model.addAttribute("hydrates", "unchecked");
            }

            if(recipeModel.getCarbohydrates()){
                model.addAttribute("carbohydrates", "checked");
            }else{
                model.addAttribute("carbohydrates", "unchecked");
            }

            if(recipeModel.getHighinfat()){
                model.addAttribute("highinfat", "checked");
            }else{
                model.addAttribute("highinfat", "unchecked");
            }

            return "recipeUpdater";
        } else {
            return "recipe";
        }
    }

    @PostMapping("/UpdateRecipe/{id}")
    public ModelAndView processUpdateRecipe(Model model, @PathVariable long id, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
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
        }
        return new ModelAndView(new RedirectView("/AdminProfile", true));
    }

    @GetMapping("/Recipes")
    public String getAllRecipes(Model model) {
        Page<Recipe> recipes = recipeService.findAll012(PageRequest.of(0, 12, Sort.by("id").descending()));
        List<Recipe> recipesModels = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipesModels.add(recipe);
        }
        model.addAttribute("recipe", recipes.getContent());
        return "recipe";
    }

    @GetMapping("/getMoreRecipes")
    public @ResponseBody Page<Recipe> getMoreProducts(Pageable page){
        return recipeService.findAll(page);
    }

    @PostMapping("/processRemoveRecipe")
    public ModelAndView processRemoveRecipe(Model model, HttpServletRequest request, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);

        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        currentUser.removeStoredRecipes(recipe.get().getId());
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/StoredRecipes", true));
    }

    @PostMapping("/processDeleteRecipe")
    public ModelAndView processDeleteRecipe(Model model, @RequestParam String id_RecipeDelete){
        long id=Long.parseLong(id_RecipeDelete);
        long longIDAux1 = 1;
        long longIDAux2 = 2;

        Recipe toRemove = recipeService.findById(id).get();

        Recipe toChange;
        if (id==longIDAux1)
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

        return new ModelAndView(new RedirectView("/Recipes", true));
    }

    @PostMapping("/processFormRecipe")
    public ModelAndView createRecipe(Model model, HttpServletRequest request, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        String creator = currentUser.getUsername();
        Date date = new Date();
        boolean vegetables=booleanos.contains("vegetables");
        boolean protein=booleanos.contains("protein");
        boolean hydrates=booleanos.contains("hydrates");
        boolean carbohydrates=booleanos.contains("carbohydrates");
        boolean highinfat=booleanos.contains("highinfat");
        Recipe recipe = new Recipe(name, time, difficulty, date, ingredients, creator, vegetables, protein, hydrates, carbohydrates, highinfat, preparation);
        recipeService.save(recipe);
        if(imageRecipe != null) {
            uploadImage(recipe.getId(), imageRecipe);
        }
        return new ModelAndView(new RedirectView("/AdminProfile", true));
    }

    @PostMapping("/processFormRecipe/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageRecipe) throws IOException {
        Recipe recipe = recipeService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();
        recipe.setHasPhoto(true);
        recipe.setRecipeImage(BlobProxy.generateProxy(imageRecipe.getInputStream(), imageRecipe.getSize()));

        recipeService.save(recipe);

        return ResponseEntity.created(location).build();
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
