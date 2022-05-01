import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Menu } from 'src/app/models/Menu/menu';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { Users } from 'src/app/models/Users/users';
import { LoginService } from 'src/app/services/Login/login.service';
import { MenuService } from 'src/app/services/Menu/menu.service';
import { UsersService } from 'src/app/services/Users/users.service';

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
}
