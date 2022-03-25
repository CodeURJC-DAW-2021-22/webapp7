package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.DietRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.security.RepositoryUserDetailsService;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<Menu> dietCreate = new ArrayList<>();

    private User currentUser=null;

    @Autowired
    private ExportPdfService exportPdfService;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private RepositoryUserDetailsService userRepository;

    @Autowired
    private RecipeService recipeService;


    @Autowired
    private MenuService menuService;

    @Autowired
    private DietService dietService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private DatabaseInit dataService;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal==null)
            model.addAttribute("logged", false);
        else {
            model.addAttribute("logged", true);
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            Optional<User> tryUser = userRepository.findByName(principal.getName());
            if (tryUser.isPresent())
                currentUser = tryUser.get();
        }
    }

    @GetMapping("/")
    public String init(Model model) {
        Optional<Recipe> outstandingRecipes = recipeService.findById(20);
        Optional<Recipe> outstandingRecipes1 = recipeService.findById(22);
        List<Recipe> outstandingRecipesAux = recipeRepository.findAll();
        int contRecipes = 0;
        for (Recipe recipe : outstandingRecipesAux)
            contRecipes++;
        double rnd = Math.random()*contRecipes;
        if ((int) rnd==0)
            rnd=rnd+1;
        Optional<Recipe> outstandingRecipes2 = recipeService.findById((long)rnd);

        Page<Recipe> recipeCarrousel1 = recipeRepository.findAll(PageRequest.of(0,3,Sort.by("id").descending()));
        Page<Recipe> recipeCarrousel2 = recipeRepository.findAll(PageRequest.of(1,3,Sort.by("id").descending()));

        double rnd1 = Math.random()*contRecipes;
        if ((int) rnd1==0)
            rnd1=rnd1+1;
        Optional<Recipe> tryRecipes1 = recipeService.findById((long)rnd1);

        double rnd2= Math.random()*contRecipes;
        if ((int)rnd2==0)
            rnd2=rnd2+1;
        Optional<Recipe> tryRecipes2 = recipeService.findById((long)rnd2);

        model.addAttribute("recipe1", outstandingRecipes.get());
        model.addAttribute("recipe2", outstandingRecipes1.get());
        model.addAttribute("recipeWeekly", outstandingRecipes2.get());
        model.addAttribute("recipeCarrousel1", recipeCarrousel1.getContent());
        model.addAttribute("recipeCarrousel2", recipeCarrousel2.getContent());
        model.addAttribute("tryRecipes1", tryRecipes1.get());
        model.addAttribute("tryRecipes2", tryRecipes2.get());
        return "index";
    }

    @GetMapping("/inicio")
    public String bbdd(Model model) throws IOException, URISyntaxException {
        dataService.init();
        return "index";
    }

    @GetMapping("/User")
    public ResponseEntity<User> getUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return ResponseEntity.ok(user);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/StoredDiets")
    public Collection<Diet> getStoredDiets(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return user.getStoredDiets();
        }
        return null;
    }


    @DeleteMapping("/processRemoveDiet/{id}")
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

    @PutMapping("/processAddDiet/{id}")
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
    @PostMapping("/processFormDiet")
    public Diet processFormDiet(@RequestParam String name){

        Diet dietNew = new Diet(name, dietCreate);
        dietRepository.save(dietNew);
        currentUser.addStoredDiets(dietNew);
        userRepository.save(currentUser);

        dietCreate.removeAll(dietCreate);

        return dietNew;
    }

    @GetMapping("/diet/{id}/{nombre}")
    public ResponseEntity<Diet> getDietPage(@PathVariable long id){

        Optional<Diet> dietId = dietService.findById(id);
        if (dietId.isPresent()) {
            Diet diet = dietId.get();
            return new ResponseEntity<>(diet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/AdminProfile")
    public ResponseEntity<User> getAdminProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

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

    @GetMapping("/Recipe/{id}")
    public ResponseEntity<Recipe> showRecipe(@PathVariable long id) {

        Optional<Recipe> recipeId = recipeService.findById(id);
        if (recipeId.isPresent()) {
            Recipe recipe = recipeId.get();
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/Recipes")
    public Collection<Recipe> getAllRecipes(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if (userPrincipal.isPresent()) {
            Page<Recipe> recipes = recipeRepository.findAll(PageRequest.of(0, 12, Sort.by("id").descending()));
            List<Recipe> recipesModels = new ArrayList<>();
            for (Recipe recipe : recipes) {
                recipesModels.add(recipe);
            }
            return recipesModels;
        }
        return null;
    }

    @GetMapping("/getMoreRecipes")
    public @ResponseBody Page<Recipe> getMoreProducts(Pageable page){

        return recipeRepository.findAll(page);
    }
    
    @DeleteMapping("/processRemoveRecipe")
    public ResponseEntity<Recipe> processRemoveRecipe(@RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);

        try {
            Optional<Recipe> recipe = recipeService.findById(id);
            currentUser.removeStoredRecipes(recipe.get().getId());
            userRepository.save(currentUser);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
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

        menuRepository.updateBeforeDelete(toChange.getId(), toRemove.getId());

        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            User uLoaded = userRepository.findById(u.getId()).get();
            List<Recipe> recipeList = uLoaded.getStoredRecipes();
            if (recipeList.contains(toRemove)) {
                uLoaded.removeStoredRecipes(toRemove.getId());
            }
            u = uLoaded;
            userRepository.save(u);
        }


        recipeService.delete(id);

        return new ModelAndView(new RedirectView("/Recipes", true));
    }


    @PostMapping("/processAddRecipe")
    public ModelAndView processAddRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().add(recipe.get());
        userRepository.save(currentUser);

        return new ModelAndView(new RedirectView("/Recipe/"+id_Recipe, true));
    }

    @PostMapping("/processFormRecipe")
    public ModelAndView processMenuMaker(Model model, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
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

    @PostMapping("/processFormMenu")
    public ModelAndView processRecipeMaker(Model model, @RequestParam String name, @RequestParam long lunchMonday, @RequestParam long lunchTuesday, @RequestParam long lunchWednesday, @RequestParam long lunchThursday, @RequestParam long lunchFriday, @RequestParam long lunchSaturday, @RequestParam long lunchSunday, @RequestParam long dinnerMonday, @RequestParam long dinnerTuesday, @RequestParam long dinnerWednesday, @RequestParam long dinnerThursday, @RequestParam long dinnerFriday, @RequestParam long dinnerSaturday, @RequestParam long dinnerSunday){
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

        return new ModelAndView(new RedirectView("/User", true));
    }

    @PostMapping("/processFormSignUp")
    public ModelAndView processRegister(Model model, @RequestParam String name, @RequestParam String password, @RequestParam String mail){
        Menu menu = menuRepository.findAll().get(1);
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();

        User user = new User(mail, name, passwordEncoder.encode(password), recipes, menu, dietas, false);

        Optional<User> tryUser = userRepository.findByName(user.getName());
        Optional<User> tryMail = userRepository.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userRepository.save(user);
            if(!user.getAdmin()) {
                model.addAttribute("loggedUser", true);
                model.addAttribute("logged",true);
            }else if(user.getAdmin()){
                model.addAttribute("admin", true);
                model.addAttribute("logged",true);
            }
            currentUser=user;
            return new ModelAndView(new RedirectView("/", true));
        }
        else {
            return new ModelAndView(new RedirectView("/SingUpError", true));
        }
    }
    @GetMapping("/processLogOut")
    public void LogOut(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.currentUser=null;
        model.addAttribute("loggedUser",false);
        model.addAttribute("logged",false);
        model.addAttribute("admin", false);
        request.logout();
        response.sendRedirect("/");
    }

    @PostMapping("/processFormLogIn")
    public ModelAndView processForm(Model model, @RequestParam String name, @RequestParam String password){
        Optional<User> tryUser = userRepository.findByName(name);
        if (tryUser.isPresent()) {
            if (tryUser.get().getPassword().equals(password)) {
                currentUser = tryUser.get();
                return new ModelAndView(new RedirectView("/", true));
            }
            else
                return new ModelAndView(new RedirectView("/LogInError", true));
        }
        else
            return new ModelAndView(new RedirectView("/LogInError", true));
    }

    @GetMapping("/StoredRecipes")
    public Collection<Recipe> getAllYourRecipes(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if(userPrincipal.isPresent()) {
            User user = userPrincipal.get();
            return currentUser.getStoredRecipes();
        }
        return null;
    }

    @GetMapping("/MenuMaker")
    public Collection<Recipe> getMenu_Maker(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if(userPrincipal.isPresent()){
            User user = userPrincipal.get();
            return  currentUser.getStoredRecipes();
        }
        return null;
    }

    @GetMapping("/MenuAll")
    public Collection<Menu> getMenu_All(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

        if (userPrincipal.isPresent()) {
            Page<Menu> menus = menuRepository.findAll(PageRequest.of(0, 12, Sort.by("id").descending()));
            List<Menu> menusModels = new ArrayList<>();
            for (Menu menu : menus) {
                menusModels.add(menu);
            }
            return menus.getContent();
        }
        return null;
    }

    @PostMapping("/processActiveMenu")
    public ModelAndView processActiveMenu(Model model, @RequestParam String id_Menu){
        long id=Long.parseLong(id_Menu);
        Optional<Menu> menu = menuService.findById(id);
        currentUser.setActiveMenu(menu.get());
        userRepository.save(currentUser);

        return new ModelAndView(new RedirectView("/YourMenu", true));
    }

    @GetMapping("/Menu/{id}")
    public ResponseEntity<Menu> getMenu_Selected(@PathVariable long id){
        Optional<Menu> selectedMenu = menuService.findById(id);
        if(selectedMenu.isPresent()){
            Menu menu = selectedMenu.get();
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/downloadReceipt")
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

    @GetMapping("/YourMenu")
    public ResponseEntity<Menu> getMenu_Activo(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userPrincipal = userRepository.findByName(principal.getName());

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

