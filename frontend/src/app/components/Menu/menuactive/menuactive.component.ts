import { Component, OnInit } from '@angular/core';
import { Menu } from './../../../models/Menu/menu';
import { Recipes } from './../../../models/Recipes/recipes';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { MenuService } from './../../../services/Menu/menu.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-menuactive',
  templateUrl: './menuactive.component.html',
  styleUrls: ['./menuactive.component.css']
})
export class MenuactiveComponent implements OnInit {
  user: Users;
  menu : Menu;
  recipeAux : Recipes;
  lunchs : Recipes[] = [];
  dinners: Recipes[]= [];

  constructor(private loginService: LoginService, userService:UsersService,recipeService:RecipesService, menuService: MenuService) {
    this.user = loginService.currentUser();
    this.menu = loginService.currentUser().activeMenu;
        this.lunchs.push(this.menu.weeklyPlan[0]);
        this.lunchs.push(this.menu.weeklyPlan[2]);
        this.lunchs.push(this.menu.weeklyPlan[4]);
        this.lunchs.push(this.menu.weeklyPlan[6]);
        this.lunchs.push(this.menu.weeklyPlan[8]);
        this.lunchs.push(this.menu.weeklyPlan[10]);
        this.lunchs.push(this.menu.weeklyPlan[12]);

        this.dinners.push(this.menu.weeklyPlan[1]);
        this.dinners.push(this.menu.weeklyPlan[3]);
        this.dinners.push(this.menu.weeklyPlan[5]);
        this.dinners.push(this.menu.weeklyPlan[7]);
        this.dinners.push(this.menu.weeklyPlan[9]);
        this.dinners.push(this.menu.weeklyPlan[11]);
        this.dinners.push(this.menu.weeklyPlan[13]);
  }

  ngOnInit(): void {
  }
  isHealthy(){
    return this.menu.healthy;
  }
  isActive(){
    return this.user.activeMenu.id== this.menu.id;
  }
  isLogged(){
    return this.loginService.isLogged();
  }
}
