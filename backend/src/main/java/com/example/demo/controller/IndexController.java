package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.DatabaseInit;
import com.example.demo.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class IndexController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private DatabaseInit dataService;

    @Autowired
    private RepositoryUserDetailsService userService;

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

        Page<Recipe> recipeCarrousel1 = recipeService.findAll03(PageRequest.of(0,3, Sort.by("id").descending()));
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

    @GetMapping("/AboutUs")
    public String getAboutUs(Model model){return "AboutUs";}

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
}
