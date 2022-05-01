package com.example.demo.controller;

import com.example.demo.model.Diet;
import com.example.demo.model.Menu;
import com.example.demo.model.User;
import com.example.demo.security.RepositoryUserDetailsService;
import com.example.demo.service.DietService;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DietController {

    private List<Menu> dietCreate = new ArrayList<>();

    @Autowired
    private MenuService menuService;

    @Autowired
    private RepositoryUserDetailsService userService;

    @Autowired
    private DietService dietService;

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
    public ModelAndView processFormDiet(Model model, HttpServletRequest request, @RequestParam String name){

        Diet dietNew = new Diet(name, dietCreate);
        dietService.save(dietNew);

        Principal principal = request.getUserPrincipal();
        Optional<User> currentUserOptional = userService.findByName(principal.getName());
        User currentUser = currentUserOptional.get();

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
}
