import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipes } from './../../../models/Recipes/recipes';
import { LoginService } from './../../../services/Login/login.service';
import { MenuService } from './../../../services/Menu/menu.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-menumaker',
  templateUrl: './menumaker.component.html',
  styleUrls: ['./menumaker.component.css']
})
export class MenumakerComponent implements OnInit{

  recipes: Recipes[];
  copy: Recipes[];

  name: string;

  lunchMonday: number;
  lunchTuesday: number;
  lunchWednesday: number;
  lunchThursday: number;
  lunchFriday: number;
  lunchSaturday: number;
  lunchSunday: number;

  dinnerMonday: number;
  dinnerTuesday: number;
  dinnerWednesday: number;
  dinnerThursday: number;
  dinnerFriday: number;
  dinnerSaturday: number;
  dinnerSunday: number;

  constructor(private loginService:LoginService ,private router:Router,public usersService : UsersService, private menuService:MenuService) { }

  ngOnInit(): void {
    this.usersService.getUserRecipes().subscribe(
      response => {
        this.recipes = response;
        this.copy = response;
        console.log(this.recipes)
      },
      error => console.error(error)
    );
  }

  save() {
    this.menuService.menuMaker(this.name, this.lunchMonday, this.lunchTuesday, this.lunchWednesday, this.lunchThursday, this.lunchFriday, this.lunchSaturday, this.lunchSunday, this.dinnerMonday, this.dinnerTuesday, this.dinnerWednesday, this.dinnerThursday, this.dinnerFriday, this.dinnerSaturday, this.dinnerSunday).subscribe(
      (response: any) => this.router.navigate(['/user']),
      (error: any) => alert("Something gone wrong")
    );
  }
  isLogged(){
    return this.loginService.isLogged();
  }
}
