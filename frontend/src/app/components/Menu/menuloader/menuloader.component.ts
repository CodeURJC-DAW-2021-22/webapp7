import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Menu } from './../../../models/Menu/menu';
import { Recipes } from './../../../models/Recipes/recipes';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { MenuService } from './../../../services/Menu/menu.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-menuloader',
  templateUrl: './menuloader.component.html',
  styleUrls: ['./menuloader.component.css']
})
export class MenuloaderComponent{

  menu: Menu;
  user: Users;

  lunches: Recipes[];
  dinners: Recipes[];

  constructor(private router:Router,activatedRoute: ActivatedRoute, private service:MenuService, public loginService: LoginService, private userService: UsersService) {
    const id = activatedRoute.snapshot.params['id'];
    this.user = loginService.currentUser();

    service.getMenu(id).subscribe(
      response => {
        this.menu = response;
        this.lunches = this.getLunch(this.menu);
        this.dinners = this.getDinner(this.menu);
      },
        error => console.error(error)
    );
  }

  getLunch(menu: Menu){
    var lunch: Recipes[] = [];
    var n = menu.weeklyPlan.length - 1;
    var i = 0;
    while(i<n){
        lunch.push(menu.weeklyPlan[i]);
        i=i+2;
    }
    return lunch;
  }

  getDinner(menu: Menu){
      var dinner: Recipes[] = [];
      var n = menu.weeklyPlan.length - 1;
      var i = 1;
      while(i<=n){
          dinner.push(menu.weeklyPlan[i]);
          i=i+2;
      }
      return dinner;
  }
  isActive(){
    return this.user.activeMenu.id== this.menu.id;
  }
  isHealthy(){
    return this.menu.healthy;
  }
  isLogged(){
    return this.loginService.isLogged();
  }
}
