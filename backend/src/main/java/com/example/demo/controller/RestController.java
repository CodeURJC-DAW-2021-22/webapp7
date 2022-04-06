package com.example.demo.controller;

import com.example.demo.model.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


public class RestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<Menu> dietCreate = new ArrayList<>();

    @Autowired
    private UserLoginService userServiceLogin;

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


    @ModelAttribute
    public ResponseEntity<User> addAttributes(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal==null)
            return ResponseEntity.notFound().build();
        else {

            Optional<User> tryUser = userService.findByName(principal.getName());
            if (tryUser.isPresent())
                currentUser = tryUser.get();
                return ResponseEntity.ok(currentUser);
        }
    }

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

    @GetMapping("/api/User")
    public ResponseEntity<User> getUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return ResponseEntity.ok(user);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/StoredDiets")
    public Collection<Diet> getStoredDiets(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return user.getStoredDiets();
        }
        return null;
    }


    @DeleteMapping("/api/ProcessRemoveDiet/{id}")
    public ResponseEntity<Menu> processRemoveDiet(@PathVariable long id){

        int position = 0;
        int positionDiet = -1;
        for(Menu diet: dietCreate) {
            if(diet.getId().equals(id)){
                positionDiet = position;
            }
            position++;
        }
        Menu menu = dietCreate.get(positionDiet);

        if (menu != null) {
            dietCreate.remove(positionDiet);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/ProcessAddDiet/{id}")
    public ResponseEntity<Menu> processAddDiet(@PathVariable long id){

        Optional<Menu> menuId = menuService.findById(id);
        if (menuId.isPresent()) {
            Menu menu = menuId.get();
            dietCreate.add(menu);
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/ProcessFormDiet")
    public Diet processFormDiet(@RequestParam String name){

        Diet dietNew = new Diet(name, dietCreate);
        dietService.save(dietNew);
        currentUser.addStoredDiets(dietNew);
        userService.save(currentUser);

        dietCreate.removeAll(dietCreate);

        return dietNew;
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

    @GetMapping("/api/AdminProfile")
    public ResponseEntity<User> getAdminProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            if(user.getAdmin()) {
                return ResponseEntity.ok(user);
            }else{
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
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

    @GetMapping("/api/RecipeUpdater/{id}")
    public ResponseEntity<Recipe> getRecipeMaker(@PathVariable long id) throws SQLException {
        Optional<Recipe> recipeId = recipeService.findById(id);
        if (recipeId.isPresent()) {
            Recipe recipe = recipeId.get();
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/Recipes")
    public Collection<Recipe> getAllRecipes(HttpServletRequest request) {

            Page<Recipe> recipes = recipeService.findAll012(PageRequest.of(0, 12, Sort.by("id").descending()));
            List<Recipe> recipesModels = new ArrayList<>();
            for (Recipe recipe : recipes) {
                recipesModels.add(recipe);
            }
            return recipesModels;

    }

    @GetMapping("/api/GetMoreRecipes")
    public @ResponseBody Page<Recipe> getMoreProducts(Pageable page){

        return recipeService.findAll(page);
    }
    
    @DeleteMapping("/api/ProcessRemoveRecipe")
    public ResponseEntity<Recipe> processRemoveRecipe(@RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);

        try {
            Optional<Recipe> recipe = recipeService.findById(id);
            currentUser.removeStoredRecipes(recipe.get().getId());
            userService.save(currentUser);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/api/ProcessDeleteRecipe")
    public ResponseEntity<Recipe> processDeleteRecipe(@RequestParam String id_RecipeDelete){
        long id=Long.parseLong(id_RecipeDelete);
        long longIDAux1 = 1;
        long longIDAux2 = 2;

        Optional<Recipe> toRemoveOptional = recipeService.findById(id);

        if (toRemoveOptional.isPresent()) {
            Recipe toRemove = toRemoveOptional.get();
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
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/api/ProcessAddRecipe")
    public ResponseEntity<Recipe> processAddRecipe(@RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            currentUser.getStoredRecipes().add(recipe);
            userService.save(currentUser);

            return new ResponseEntity<>(recipe, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/UpdateRecipe/{id}")
    public Recipe processUpdateRecipe(@PathVariable long id, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
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
            return recipe;
        }
        return null;
    }

    @PostMapping("/api/ProcessFormRecipe")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe processMenuMaker(@RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
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
        return recipe;
    }

    @PostMapping("/api/ProcessFormMenu")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu processRecipeMaker(@RequestParam String name, @RequestParam long lunchMonday, @RequestParam long lunchTuesday, @RequestParam long lunchWednesday, @RequestParam long lunchThursday, @RequestParam long lunchFriday, @RequestParam long lunchSaturday, @RequestParam long lunchSunday, @RequestParam long dinnerMonday, @RequestParam long dinnerTuesday, @RequestParam long dinnerWednesday, @RequestParam long dinnerThursday, @RequestParam long dinnerFriday, @RequestParam long dinnerSaturday, @RequestParam long dinnerSunday){
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

        return menu;
    }

    @PostMapping("/api/ProcessFormSignUp")
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
            currentUser=user;
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/ProcessLogOut")
    public ResponseEntity<AuthResponse> LogOut(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok(new AuthResponse(AuthResponse.Status.SUCCESS, userServiceLogin.logout(request, response)));
    }

    @PostMapping("/api/ProcessFormLogIn")
    public ResponseEntity<AuthResponse> processForm(
        @CookieValue(name = "accessToken", required = false) String accessToken,
        @CookieValue(name = "refreshToken", required = false) String refreshToken,
        @RequestBody LoginRequest loginRequest){

            return userServiceLogin.login(loginRequest, accessToken, refreshToken);

    }

    @PostMapping("/api/ProcessRefresh")
    public ResponseEntity<AuthResponse> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

        return userServiceLogin.refresh(refreshToken);
    }

    @GetMapping("/api/StoredRecipes")
    public Collection<Recipe> getAllYourRecipes(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return currentUser.getStoredRecipes();
        }
        return null;
    }

    @GetMapping("/api/MenuMaker")
    public Collection<Recipe> getMenu_Maker(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()){
            User user = userPrincipal.get();
            return  currentUser.getStoredRecipes();
        }
        return null;
    }

    @GetMapping("/api/MenuAll")
    public Page<Menu> getMenu_All(HttpServletRequest request, Pageable page){
        return menuService.findAllMenu(page);
    }

    @PostMapping("/api/ProcessActiveMenu")
    public ResponseEntity<Menu> processActiveMenu(@RequestParam String id_Menu){
        long id=Long.parseLong(id_Menu);
        Optional<Menu> menuOpcional = menuService.findById(id);

        if(menuOpcional.isPresent()) {
            Menu menu = menuOpcional.get();
            currentUser.setActiveMenu(menu);
            userService.save(currentUser);

            return new ResponseEntity<>(menu, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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

    @GetMapping("/api/DownloadReceipt")
    public void downloadReceipt(HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        User user = currentUser;
        data.put("client", user);

        Menu menu = currentUser.getActiveMenu();
        ArrayList<ReceiptItem> receiptItems = new ArrayList<ReceiptItem>();
        List<Recipe> recipesMenu = menu.getWeeklyPlan();

        for (int i=0; i<=13;i=i+1) {
            Recipe recipe = recipesMenu.get(i);
            ReceiptItem item = new ReceiptItem();
            item.setName(recipe.getName());

            String allIngredientsRecipe = recipe.getIngredients();
            String[] strArr = allIngredientsRecipe.split(",\\s+");
            ArrayList<String> list = new ArrayList<String>(Arrays.asList(strArr));
            String ultimo = list.get(list.size() - 1);
            list.remove(list.size() - 1);

            String[] strArrWithY = ultimo.split("\\s+y\\s+");
            ArrayList<String> listWithY = new ArrayList<String>(Arrays.asList(strArrWithY));
            list.addAll(listWithY);

            item.setIngredients(list);
            receiptItems.add(item);
        }
        data.put("item", receiptItems);


        ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("Receipt", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
    }

    @GetMapping("/api/YourMenu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userService.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            Optional<Menu> tryMenu = menuService.findById(user.getActiveMenu().getId());

            if(tryMenu.isPresent()){
                Menu menu = tryMenu.get();
                return new ResponseEntity<>(menu, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/ProcessFormRecipe/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageRecipe) throws IOException {
        Recipe recipe = recipeService.findById(id).orElseThrow();

        URI location = fromCurrentRequest().build().toUri();
        recipe.setHasPhoto(true);
        recipe.setRecipeImage(BlobProxy.generateProxy(imageRecipe.getInputStream(), imageRecipe.getSize()));

        recipeService.save(recipe);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/Recipe/{id}/image")
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

