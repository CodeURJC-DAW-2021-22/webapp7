package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.MenuRepository;
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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


@Controller
public class WebController {

    private User currentUser=null;

    @Autowired
    private ExportPdfService exportPdfService;

    @Autowired
    private MenuRepository menuRepository;

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

    @GetMapping("/Recipe")
    public String getRecipe(Model model){return "details";}

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
        List<Diet> idDietas= this.currentUser.getStoredDiets();
        List<Diet> dietas = new ArrayList<>();

        for (Diet d : idDietas){
            long id = d.getId();
            System.out.println(d.getNombre());
            Optional<Diet> tryDiet = dietService.findById(id);
            if (tryDiet.isPresent())
                dietas.add(tryDiet.get());
        }
        for (Diet d : dietas){
            System.out.println(d.getNombre());
        }
        model.addAttribute("diets",dietas);
        return "DropDown";
    }

    @GetMapping("/diet/{id}/{nombre}")
    public String getDietPage(Model model, @PathVariable long id){
        Optional<Diet> d = dietService.findById(id);
        List<Menu> menuList = new ArrayList<>();
        if (d.isPresent())
            for (Menu m : d.get().getDieta()){
                if (!menuList.contains(m))
                    menuList.add(m);
            }
            model.addAttribute("DietName",d.get().getNombre());
            model.addAttribute("menuList",menuList);
        return "diet";
    }

    @GetMapping("/AdminProfile")
    public String getAdminProfile(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("usersAll", users.size());

        return "Admin";}

    @GetMapping("/RecipeMaker")
    public String getRecipeMaker(Model model){return "RecipeMaker";}

    @GetMapping("/Recipe/{id}")
    public String showRecipe(Model model, @PathVariable long id) {
        if(currentUser != null) {
            if (currentUser.getStoredRecipes().stream().anyMatch(r -> r.getId() == id)) {
                model.addAttribute("disable", true);
            } else if (currentUser.getAdmin()) {
                model.addAttribute("adminDelete", true);
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
    public String getAllRecipes(Model model){
        Page<Recipe> recipes = recipeRepository.findAll(PageRequest.of(0,12,Sort.by("id").descending()));
        List<Recipe> recipesModels = new ArrayList<>();
        for (Recipe recipe: recipes){
            recipesModels.add(recipe);
        }
        model.addAttribute("recipe", recipes.getContent());

        return "recipe";
    }

    @GetMapping("/getMoreRecipes")
    public @ResponseBody Page<Recipe> getMoreProducts(Pageable page){
        return recipeRepository.findAll(page);
    }

    @PostMapping("/processRemoveRecipe")
    public ModelAndView processRemoveRecipe(Model model, @RequestParam String id_Recipe){
        long id=Long.parseLong(id_Recipe);
        Optional<Recipe> recipe = recipeService.findById(id);
        currentUser.removeStoredRecipes(recipe.get().getId());
        userRepository.save(currentUser);

        return new ModelAndView(new RedirectView("/StoredRecipes", true));
    }

    @PostMapping("/processDeleteRecipe")
    public ModelAndView processDeleteRecipe(Model model, @RequestParam String id_RecipeDelete){
        long id=Long.parseLong(id_RecipeDelete);
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
        Menu menuVoid = new Menu();
        List<Diet> dietas = new ArrayList<Diet>();
        List<Recipe> recipes = new ArrayList<Recipe>();
        menuRepository.save(menuVoid);

        User user = new User(mail, name, password, recipes, menuVoid, dietas, false);

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
        Page<Menu> menus = menuRepository.findAll(PageRequest.of(0,12,Sort.by("id").descending()));
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
        userRepository.save(currentUser);

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

            ArrayList<Integer> l = new ArrayList<>();
            for (int i=15; i>=0;i=i-5){
                l.add(i);
            }
            model.addAttribute("indexGraphic",l);

            int[] scoreArray = selectedMenu.get().getMenuScore();

            model.addAttribute("number of bars",14);

            float score = (scoreArray[0] / 15)*100;
            model.addAttribute("vegetablesStandard",score);
            score = (scoreArray[1] / 15)*100;
            model.addAttribute("vegetablesMenu",score);
            score = (scoreArray[2] / 15)*100;
            model.addAttribute("proteinStandard",score);
            score = (scoreArray[3] / 15)*100;
            model.addAttribute("proteinMenu",score);
            score = (scoreArray[4] / 15)*100;
            model.addAttribute("hydratesStandard",score);
            score = (scoreArray[5] / 15)*100;
            model.addAttribute("hydratesMenu",score);
            score = (scoreArray[6] / 15)*100;
            model.addAttribute("carboHydratesStandard",score);
            score = (scoreArray[7] / 15)*100;
            model.addAttribute("carboHydratesMenu",score);
            score = (scoreArray[8] / 15)*100;
            model.addAttribute("highInFatStandard",score);
            score = (scoreArray[9] / 15)*100;
            model.addAttribute("highInFatMenu",score);
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

            ArrayList<Integer> l = new ArrayList<>();
            for (int i=15; i>=0;i=i-5){
                l.add(i);
            }
            model.addAttribute("indexGraphic",l);

            int[] scoreArray = tryMenu.get().getMenuScore();

            model.addAttribute("number of bars",14);

            float score = (scoreArray[0] / 15)*100;
            model.addAttribute("vegetablesStandard",score);
            score = (scoreArray[1] / 15)*100;
            model.addAttribute("vegetablesMenu",score);
            score = (scoreArray[2] / 15)*100;
            model.addAttribute("proteinStandard",score);
            score = (scoreArray[3] / 15)*100;
            model.addAttribute("proteinMenu",score);
            score = (scoreArray[4] / 15)*100;
            model.addAttribute("hydratesStandard",score);
            score = (scoreArray[5] / 15)*100;
            model.addAttribute("hydratesMenu",score);
            score = (scoreArray[6] / 15)*100;
            model.addAttribute("carboHydratesStandard",score);
            score = (scoreArray[7] / 15)*100;
            model.addAttribute("carboHydratesMenu",score);
            score = (scoreArray[8] / 15)*100;
            model.addAttribute("highInFatStandard",score);
            score = (scoreArray[9] / 15)*100;
            model.addAttribute("highInFatMenu",score);
        }

        model.addAttribute("lunchs",lunches);
        model.addAttribute("dinners",dinners);
        return "Menu_Activo";}

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
