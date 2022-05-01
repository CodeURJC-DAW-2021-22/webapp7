package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.RecipeRepository;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.*;
import org.apache.commons.io.IOUtils;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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



public class WebController {

    @Autowired
    private PasswordEncoder passwordEncoder;

	private List<Menu> dietCreate = new ArrayList<>();

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
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal==null)
            model.addAttribute("logged", false);
        else {
            model.addAttribute("logged", true);
            model.addAttribute("admin", request.isUserInRole("ADMIN"));
            Optional<User> tryUser = userService.findByName(principal.getName());
            if (tryUser.isPresent())
                currentUser = tryUser.get();
        }
    }

    @GetMapping("/")
    public String init(Model model) {
        Optional<Recipe> outstandingRecipes = recipeService.findById(20);
        Optional<Recipe> outstandingRecipes1 = recipeService.findById(22);
        List<Recipe> outstandingRecipesAux = recipeService.findAll();
        int contRecipes = 0;
        for (Recipe recipe : outstandingRecipesAux)
            contRecipes++;
        double rnd = Math.random()*contRecipes;
        if ((int) rnd==0)
            rnd=rnd+1;
        Optional<Recipe> outstandingRecipes2 = recipeService.findById((long)rnd);

        Page<Recipe> recipeCarrousel1 = recipeService.findAll03(PageRequest.of(0,3,Sort.by("id").descending()));
        Page<Recipe> recipeCarrousel2 = recipeService.findAll13(PageRequest.of(1,3,Sort.by("id").descending()));

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

    @GetMapping("/LogIn")
    public String getLogin(Model model){
        return("login");
    }

    @GetMapping("/LogInError")
    public String getLoginError(Model model){
        return "loginerror";
    }
    @GetMapping("/SingUpError")
    public String getSignUpError(Model model){
        return "singuperror";
    }

    @GetMapping("/User")
    public String getUser(Model model){
        model.addAttribute("username",currentUser.getUsername());
        model.addAttribute("mail",currentUser.getMail());
        model.addAttribute("password",currentUser.getPassword());
        return "user";}

    @GetMapping("/AboutUs")
    public String getAboutUs(Model model){return "AboutUs";}

    @GetMapping("/StoredDiets")
    public String getStoredDiets(Model model){
        List<Diet> dietas = this.currentUser.getStoredDiets();
        model.addAttribute("diets",dietas);
        return "DropDown";
    }

	@GetMapping("/DietMaker")
    public String getDiet_Maker(Model model){
        List<Menu> menuAll = menuService.findAll();
        List<Menu> menuContains = new ArrayList<>();
        for (Menu menu: menuAll){
            boolean conteins = false;
            for(Menu diet: dietCreate) {
                if(diet.getId().equals(menu.getId())){
                    conteins = true;
                }
            }
            if(!conteins){
                menuContains.add(menu);
            }
        }
        model.addAttribute("menuList", dietCreate);
        model.addAttribute("menuContains", menuContains);

        return "DietMaker";
    }

    @PostMapping("/processRemoveDiet/{id}")
    public ModelAndView processRemoveDiet(Model model, @PathVariable long id){
        int position = 0;
        int positionDiet = -1;
        for(Menu diet: dietCreate) {
            if(diet.getId().equals(id)){
                positionDiet = position;
            }
            position++;
        }
        dietCreate.remove(positionDiet);
        return new ModelAndView(new RedirectView("/DietMaker", true));
    }

    @PostMapping("/processAddDiet/{id}")
    public ModelAndView processAddDiet(Model model, @PathVariable long id){
        Menu menu = menuService.findById(id).get();
        dietCreate.add(menu);
        return new ModelAndView(new RedirectView("/DietMaker", true));
    }

    @PostMapping("/processFormDiet")
    public ModelAndView processFormDiet(Model model, @RequestParam String name){

        Diet dietNew = new Diet(name, dietCreate);
        dietService.save(dietNew);
        currentUser.addStoredDiets(dietNew);
        userService.save(currentUser);

        dietCreate.removeAll(dietCreate);

        return new ModelAndView(new RedirectView("/", true));
    }

    @GetMapping("/diet/{id}/{nombre}")
    public String getDietPage(Model model, @PathVariable long id){
        Optional<Diet> d = dietService.findById(id);
        List<Menu> menuList = new ArrayList<>();
        if (d.isPresent())
            for (Menu m : d.get().getMenus()){
                if (!menuList.contains(m))
                    menuList.add(m);
            }
            model.addAttribute("DietName",d.get().getName());
            model.addAttribute("menuList",menuList);
        return "diet";
    }

    @GetMapping("/AdminProfile")
    public String getAdminProfile(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("usersAll", users.size());
        model.addAttribute("adminName", this.currentUser.getName());

        int healthyUsers=0;
        int unhealthyUsers=0;
        for (User u : users){
            Optional<Menu> tryMenu = menuService.findById(u.getActiveMenu().getId());
            if (tryMenu.isPresent()){
                Menu menu = tryMenu.get();
                if (menu.isHealthy())
                    healthyUsers++;
                else
                    unhealthyUsers++;
            }
        }
        model.addAttribute("numberOfHealthyUsers", healthyUsers);
        model.addAttribute("numberOfUnhealthyUsers",unhealthyUsers);
        return "Admin";}

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

    @PostMapping("/processUpdateRecipe/{id}")
    public ModelAndView processUpdateRecipe(Model model, @PathVariable long id, @RequestParam String name, @RequestParam int time, @RequestParam String difficulty, @RequestParam String preparation, @RequestParam String ingredients, @RequestParam List<String> booleanos, @RequestParam MultipartFile imageRecipe) throws IOException {
        Optional<Recipe> recipeOpt = recipeService.findById(id);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            boolean vegetables=booleanos.contains("vegetables");
            boolean protein=booleanos.contains("protein");
            boolean hydrates=booleanos.contains("hydrates");
            boolean carbohydrates=booleanos.contains("carbohydrates");
            boolean highinfat=booleanos.contains("highinfat");


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
            return new ModelAndView(new RedirectView("/Recipes", true));
        }
        return new ModelAndView(new RedirectView("/Recipes", true));
    }

    @GetMapping("/Recipe/{id}")
    public String showRecipe(Model model, @PathVariable long id) {
        if(currentUser != null) {
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
    public ModelAndView processRemoveRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
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

    @PostMapping("/processAddRecipe")
    public ModelAndView processAddRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.getStoredRecipes().add(recipe.get());
        userService.save(currentUser);

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
        Menu menu = menuService.findAll().get(1);
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();

        User user = new User(mail, name, passwordEncoder.encode(password), recipes, menu, dietas, false);

        Optional<User> tryUser = userService.findByName(user.getName());
        Optional<User> tryMail = userService.findByMail(user.getMail());
        if (!tryUser.isPresent() && !tryMail.isPresent()) {
            userService.save(user);
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
        Optional<User> tryUser = userService.findByName(name);
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
    public String getAllYourRecipes(Model model){
        List<Recipe> recipes = currentUser.getStoredRecipes();
        model.addAttribute("yourRecipe", recipes);

        return "Stored_Recipes";
    }

    @GetMapping("/MenuMaker")
    public String getMenu_Maker(Model model){
        List<Recipe> recipes = currentUser.getStoredRecipes();

        model.addAttribute("recipe", recipes);

        return "MenuMaker";
    }

    @GetMapping("/MenuAll")
    public String getMenu_All(Model model){
        Page<Menu> menus = menuService.findAll(PageRequest.of(0,12,Sort.by("id").descending()));
        List<Menu> menusModels = new ArrayList<>();
        for (Menu menu: menus){
            menusModels.add(menu);
        }
        model.addAttribute("menu", menus.getContent());

        return "menuAll";
    }

    @PostMapping("/processActiveMenu")
    public ModelAndView processActiveMenu(Model model, @RequestParam String id_Menu){
        long id=Long.parseLong(id_Menu);
        Optional<Menu> menu = menuService.findById(id);
        currentUser.setActiveMenu(menu.get());
        userService.save(currentUser);

        return new ModelAndView(new RedirectView("/YourMenu", true));
    }
    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
    @GetMapping("/Menu/{id}")
    public String getMenu_Selected(Model model, @PathVariable long id){
        if (currentUser.getActiveMenu().getId().equals(id)) {
            model.addAttribute("disable", true);
        } else {
            model.addAttribute("userMenu", currentUser != null);
        }

        List<Recipe> lunches = new ArrayList<>();
        List<Recipe> dinners = new ArrayList<>();
        Optional<Menu> selectedMenu = menuService.findById(id);
        if(selectedMenu.isPresent()){
            lunches = selectedMenu.get().getLunchs();
            dinners = selectedMenu.get().getDinners();
            model.addAttribute("menu",selectedMenu.get());

            int[] scoreArray = new int[5];

            Optional<Menu> tryMenuForScore = menuService.findById(selectedMenu.get().getId());

            if (tryMenuForScore.isPresent()) {
                int cont=0;
                for (int i :tryMenuForScore.get().getMenuScore()){
                    scoreArray[cont] = i;
                    System.out.println(scoreArray[cont]);
                    cont++;
                }
            }

            model.addAttribute("vegetablesStandard",8);
            model.addAttribute("vegetablesMenu",scoreArray[0]);
            model.addAttribute("proteinStandard",5);
            model.addAttribute("proteinMenu",scoreArray[1]);
            model.addAttribute("hydratesStandard",3);
            model.addAttribute("hydratesMenu",scoreArray[2]);
            model.addAttribute("carboHydratesStandard",2);
            model.addAttribute("carboHydratesMenu",scoreArray[3]);
            model.addAttribute("highInFatStandard",1);
            model.addAttribute("highInFatMenu",scoreArray[4]);
            model.addAttribute("IsMenuHealthy",tryMenuForScore.get().isHealthy());
            model.addAttribute("menuScore",tryMenuForScore.get().getScore());
        }

        model.addAttribute("lunchs",lunches);
        model.addAttribute("dinners",dinners);
        return "menuLoader";}

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
    public String getMenu_Activo(Model model){

        List<Recipe> lunches = new ArrayList<>();
        List<Recipe> dinners = new ArrayList<>();

        Optional<Menu> tryMenu = menuService.findById(currentUser.getActiveMenu().getId());
        if(tryMenu.isPresent()){
            lunches = tryMenu.get().getLunchs();
            dinners = tryMenu.get().getDinners();
            model.addAttribute("menu",tryMenu.get());

            int[] scoreArray = new int[5];

            Optional<Menu> tryMenuForScore = menuService.findById(tryMenu.get().getId());

            if (tryMenuForScore.isPresent()) {
                int cont=0;
                for (int i :tryMenuForScore.get().getMenuScore()){
                    scoreArray[cont] = i;
                    cont++;
                }
            }

            model.addAttribute("vegetablesStandard",8);
            model.addAttribute("vegetablesMenu",scoreArray[0]);
            model.addAttribute("proteinStandard",5);
            model.addAttribute("proteinMenu",scoreArray[1]);
            model.addAttribute("hydratesStandard",3);
            model.addAttribute("hydratesMenu",scoreArray[2]);
            model.addAttribute("carboHydratesStandard",2);
            model.addAttribute("carboHydratesMenu",scoreArray[3]);
            model.addAttribute("highInFatStandard",1);
            model.addAttribute("highInFatMenu",scoreArray[4]);
            model.addAttribute("IsMenuHealthy",tryMenuForScore.get().isHealthy());
            model.addAttribute("menuScore",tryMenuForScore.get().getScore());
        }

        model.addAttribute("lunchs",lunches);
        model.addAttribute("dinners",dinners);
        return "ActiveMenu";}

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
