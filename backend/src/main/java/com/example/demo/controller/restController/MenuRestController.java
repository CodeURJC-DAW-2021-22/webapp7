package com.example.demo.controller.restController;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/Menus")
public class MenuRestController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenu_Selected(@PathVariable long id){
        Optional<Menu> selectedMenu = menuService.findById(id);
        if(selectedMenu.isPresent()){
            Menu menu = selectedMenu.get();
            return new ResponseEntity<>(menu, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Menu processRecipeMaker(@RequestParam String name, @RequestParam long lunchMonday, @RequestParam long lunchTuesday, @RequestParam long lunchWednesday, @RequestParam long lunchThursday, @RequestParam long lunchFriday, @RequestParam long lunchSaturday, @RequestParam long lunchSunday, @RequestParam long dinnerMonday, @RequestParam long dinnerTuesday, @RequestParam long dinnerWednesday, @RequestParam long dinnerThursday, @RequestParam long dinnerFriday, @RequestParam long dinnerSaturday, @RequestParam long dinnerSunday){return null;}

    @GetMapping("/")
    public Page<Menu> getMenu_All(HttpServletRequest request, Pageable page){
        return null;
    }
}
