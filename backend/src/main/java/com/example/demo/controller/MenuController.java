package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.model.ReceiptItem;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.ExportPdfService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class MenuController {

    @Autowired
    private ExportPdfService exportPdfService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RecipeService recipeService;

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

    @GetMapping("/Menu/{id}")
    public String getMenu_Selected(Model model, HttpServletRequest request, @PathVariable long id){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

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
        return "menuLoader";
    }

    @GetMapping("/MenuAll")
    public String getMenu_All(Model model){
        Page<Menu> menus = menuService.findAll(PageRequest.of(0,12, Sort.by("id").descending()));
        List<Menu> menusModels = new ArrayList<>();
        for (Menu menu: menus){
            menusModels.add(menu);
        }
        model.addAttribute("menu", menus.getContent());

        return "menuAll";
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

    @GetMapping("/MenuMaker")
    public String getMenu_Maker(Model model, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

        List<Recipe> recipes = currentUser.getStoredRecipes();

        model.addAttribute("recipe", recipes);

        return "MenuMaker";
    }

    @GetMapping("/downloadReceipt")
    public void downloadReceipt(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Map<String, Object> data = new HashMap<>();

        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

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
}
