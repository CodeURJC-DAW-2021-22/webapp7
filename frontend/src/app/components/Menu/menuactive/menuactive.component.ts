import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/models/Menu/menu';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { Users } from 'src/app/models/Users/users';
import { LoginService } from 'src/app/services/Login/login.service';
import { MenuService } from 'src/app/services/Menu/menu.service';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';
import { UsersService } from 'src/app/services/Users/users.service';
import { jsPDF } from 'jspdf';

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
  doc = new jsPDF();
  

  constructor(private loginService: LoginService, userService:UsersService,recipeService:RecipesService, menuService: MenuService) {
    this.doc.setFontSize(30);
    this.doc.text('Receipt', 20, 20);
    this.doc.setFontSize(9);
    this.doc.text('Bill To:', 20, 30);
    this.user = loginService.currentUser();
    this.doc.setFontSize(12);
    this.doc.text('Company Name: ' + this.user.name, 20, 37);
    this.doc.text('Email: ' + this.user.mail, 20, 43);
    userService.getUserMenu().subscribe(
      response => {
        this.menu = response;

        this.doc.setFontSize(13);
        this.doc.text('Ingredients: ', 20, 55);
        this.doc.setFontSize(8);

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

        var espacio = 0;
        for (let index = 0; index < 14; index++) {
          this.doc.text(this.menu.weeklyPlan[index].name, 20, 60 + espacio);
          this.doc.text(this.menu.weeklyPlan[index].ingredients, 80, 60 + espacio);
          espacio += 5;
        }
        
      },
      error => console.error(error) 
    );  
  }

  ngOnInit(): void {
  }
  
  isHealthy(){
    return this.menu.healthy;
  }

  receiptPDF(id: number){
        this.doc.save('Receipt.pdf'); 
  }

  isLogged(){
    return this.loginService.isLogged();
  }
}
