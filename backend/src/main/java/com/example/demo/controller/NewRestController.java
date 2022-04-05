package com.example.demo.controller;
import com.example.demo.model.*;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.security.jwt.AuthResponse;
import com.example.demo.security.jwt.LoginRequest;
import com.example.demo.security.jwt.UserLoginService;
import com.example.demo.service.*;
import org.apache.commons.io.IOUtils;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;



public class NewRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<Menu> dietCreate = new ArrayList<>();

    @Autowired
    private UserLoginService userLoginService;

    private User currentUser=null;

    @Autowired
    private ExportPdfService exportPdfService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DietService dietService;

    @Autowired
    private DatabaseInit dataService;

    @GetMapping("/api")
    public Collection<Recipe> init() {
        List<Recipe> recipesModels = new ArrayList<>();
        Optional<Recipe> outstandingRecipes = recipeService.findById(20);
        if(outstandingRecipes.isPresent()){
            recipesModels.add(outstandingRecipes.get());
        }else{
            return null;
        }

        Optional<Recipe> outstandingRecipes1 = recipeService.findById(22);
        if(outstandingRecipes1.isPresent()){
            recipesModels.add(outstandingRecipes1.get());
        }else{
            return null;
        }

        List<Recipe> outstandingRecipesAux = recipeService.findAll();
        int contRecipes = 0;
        for (Recipe recipe : outstandingRecipesAux)
            contRecipes++;
        double rnd = Math.random()*contRecipes;
        if ((int) rnd==0)
            rnd=rnd+1;
        Optional<Recipe> outstandingRecipes2 = recipeService.findById((long)rnd);
        if(outstandingRecipes2.isPresent()){
            recipesModels.add(outstandingRecipes2.get());
        }else{
            return null;
        }

        Page<Recipe> recipeCarrousel1 = recipeService.findAll03(PageRequest.of(0,3,Sort.by("id").descending()));
        Page<Recipe> recipeCarrousel2 = recipeService.findAll13(PageRequest.of(1,3,Sort.by("id").descending()));
        for(Recipe recipe1 : recipeCarrousel1){
            recipesModels.add(recipe1);
        }
        for(Recipe recipe2 : recipeCarrousel2){
            recipesModels.add(recipe2);
        }

        double rnd1 = Math.random()*contRecipes;
        if ((int) rnd1==0)
            rnd1=rnd1+1;
        Optional<Recipe> tryRecipes1 = recipeService.findById((long)rnd1);
        if(tryRecipes1.isPresent()){
            recipesModels.add(tryRecipes1.get());
        }else{
            return null;
        }

        double rnd2= Math.random()*contRecipes;
        if ((int)rnd2==0)
            rnd2=rnd2+1;
        Optional<Recipe> tryRecipes2 = recipeService.findById((long)rnd2);
        if(tryRecipes2.isPresent()){
            recipesModels.add(tryRecipes2.get());
        }else{
            return null;
        }
        return recipesModels;
    }

    @GetMapping("/api/Inicio")
    public void bbdd() throws IOException, URISyntaxException {
        dataService.init();
    }

    @GetMapping("/api/Diet/{id}")
    public ResponseEntity<Diet> getDietPage(@PathVariable long id){

        Optional<Diet> dietId = dietService.findById(id);
        if (dietId.isPresent()) {
            Diet diet = dietId.get();
            return new ResponseEntity<>(diet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/Recipe/{id}")
    public ResponseEntity<Recipe> showRecipe(@PathVariable long id) {

        Optional<Recipe> recipeId = recipeService.findById(id);
        if (recipeId.isPresent()) {
            Recipe recipe = recipeId.get();
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/api/LogOut")
    public ResponseEntity<AuthResponse> LogOut(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userLoginService.logout(request, response)));
    }

    @PostMapping("/api/LogIn")
    public ResponseEntity<AuthResponse> processForm(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            @RequestBody LoginRequest loginRequest){
        return userLoginService.login(loginRequest, accessToken, refreshToken);

    }
    @GetMapping("/api/Menu/{id}")
    public ResponseEntity<Menu> getMenu_Selected(@PathVariable long id){
        Optional<Menu> selectedMenu = menuService.findById(id);
        if(selectedMenu.isPresent()){
            Menu menu = selectedMenu.get();
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }










    @GetMapping("/api/MenuMaker")
    public Collection<Recipe> getMenu_Maker(HttpServletRequest request){return null;}

    @GetMapping("/api/User")
    public ResponseEntity<User> getUser(HttpServletRequest request){ return null;}

    @GetMapping("/api/StoredDiets")
    public Collection<Diet> getStoredDiets(HttpServletRequest request){return null;}

    @DeleteMapping("/api/ProcessRemoveDiet/{id}")
    public ResponseEntity<Menu> processRemoveDiet(@PathVariable long id){return null;}

    @PutMapping("/api/ProcessAddDiet/{id}")
    public ResponseEntity<Menu> processAddDiet(@PathVariable long id){return null;}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/ProcessFormDiet")
    public Diet processFormDiet(@RequestParam String name){return null;}

    @GetMapping("/api/AdminProfile")
    public ResponseEntity<User> getAdminProfile(HttpServletRequest request){return null;}

    @GetMapping("/api/RecipeUpdater/{id}")
    public ResponseEntity<Recipe> getRecipeMaker(@PathVariable long id) throws SQLException {return null;}

    @GetMapping("/api/Recipes")
    public Collection<Recipe> getAllRecipes(HttpServletRequest request) {return null;}

    @GetMapping("/api/GetMoreRecipes")
    public @ResponseBody Page<Recipe> getMoreProducts(Pageable page){return null;}

    @DeleteMapping("/api/ProcessRemoveRecipe")
    public ResponseEntity<Recipe> processRemoveRecipe(@RequestParam String id_Recipe){return null;}

    @DeleteMapping("/api/ProcessDeleteRecipe")
    public ResponseEntity<Recipe> processDeleteRecipe(@RequestParam String id_RecipeDelete){return null;}

    @PostMapping("/api/ProcessAddRecipe")
    public ResponseEntity<Recipe> processAddRecipe(@RequestParam String id_Recipe){return null;}

    @PostMapping("/api/UpdateRecipe/{id}")
    public Recipe processUpdateRecipe(@PathVariable long id, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {return null;}

    @PostMapping("/api/ProcessFormRecipe")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe processMenuMaker(@RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {return null;}

    @PostMapping("/api/ProcessFormMenu")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu processRecipeMaker(@RequestParam String name, @RequestParam long lunchMonday, @RequestParam long lunchTuesday, @RequestParam long lunchWednesday, @RequestParam long lunchThursday, @RequestParam long lunchFriday, @RequestParam long lunchSaturday, @RequestParam long lunchSunday, @RequestParam long dinnerMonday, @RequestParam long dinnerTuesday, @RequestParam long dinnerWednesday, @RequestParam long dinnerThursday, @RequestParam long dinnerFriday, @RequestParam long dinnerSaturday, @RequestParam long dinnerSunday){return null;}

    @PostMapping("/api/ProcessFormSignUp")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> processRegister(@RequestParam String name, @RequestParam String password, @RequestParam String mail){return null;}

    @PostMapping("/api/ProcessRefresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

        return userLoginService.refresh(refreshToken);
    }
    @GetMapping("/api/StoredRecipes")
    public Collection<Recipe> getAllYourRecipes(HttpServletRequest request){return null;}

    @GetMapping("/api/MenuAll")
    public Page<Menu> getMenu_All(HttpServletRequest request, Pageable page){
        return null;
    }

    @PostMapping("/api/ProcessActiveMenu")
    public ResponseEntity<Menu> processActiveMenu(@RequestParam String id_Menu){return null;}

    @GetMapping("/api/DownloadReceipt")
    public void downloadReceipt(HttpServletResponse response) throws IOException {}

    @GetMapping("/api/YourMenu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request){return null;}

    @PostMapping("/api/ProcessFormRecipe/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageRecipe) throws IOException {return null;}

    @GetMapping("/api/Recipe/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {return null;}



    }
