import { Component, OnInit } from '@angular/core';
import { Menu } from './../../../models/Menu/menu';
import { Recipes } from './../../../models/Recipes/recipes';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { MenuService } from './../../../services/Menu/menu.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';
import { UsersService } from './../../../services/Users/users.service';
import jsPDF from 'jspdf';
import { ChartOptions, ChartType, ChartDataset } from 'chart.js';

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

  vegetablesMenu: number;
  vegetablesStandard: number = 8;
  proteinMenu: number;
  proteinStandard: number = 5;
  hydratesMenu: number;
  hydratesStandard: number = 3;
  carboHydratesMenu: number;
  carboHydratesStandard: number = 2;
  highInFatMenu: number;
  highInFatStandard: number = 1;

  barChartOptions: ChartOptions = {
    responsive: false,
  };
  barChartLabels: string[] = ['Your vegetables', 'Usual Vegetables', 'Your Proteins', 'Usual Protein', 'Your Hydrates', 'Usual Hydrates', 'Your c.Hydrates', 'Usual c.Hydrates', 'Your Fat', 'Usual Fat'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataset[];

  constructor(private loginService: LoginService, userService:UsersService,recipeService:RecipesService, menuService: MenuService) {
    this.doc.setFontSize(30);
    this.doc.text('Receipt', 20, 20);
    this.doc.setFontSize(9);
    this.doc.text('Bill To:', 20, 30);
    this.user = loginService.currentUser();
    this.menu = loginService.currentUser().activeMenu;
    this.doc.setFontSize(12);
    this.doc.text('Company Name: ' + this.user.name, 20, 37);
    this.doc.text('Email: ' + this.user.mail, 20, 43);
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

        var scoreMenu = [0, 0, 0, 0, 0];
        var total = this.menu.weeklyPlan.length;
        for(let i = 0; i < total;i++){
        if(this.menu.weeklyPlan[i].vegetables){
          scoreMenu[0]=scoreMenu[0]+1;
        }
        if(this.menu.weeklyPlan[i].protein)
                scoreMenu[1]=scoreMenu[1]+1;
        if(this.menu.weeklyPlan[i].hydrates)
                scoreMenu[2]=scoreMenu[2]+1;
        if(this.menu.weeklyPlan[i].carbohydrates)
                scoreMenu[3]=scoreMenu[3]+1;
        if(this.menu.weeklyPlan[i].highinfat)
                scoreMenu[4]=scoreMenu[4]+1;
        }

        this.vegetablesMenu = scoreMenu[0];
        this.proteinMenu = scoreMenu[1];
        this.hydratesMenu = scoreMenu[2];
        this.carboHydratesMenu = scoreMenu[3];
        this.highInFatMenu = scoreMenu[4];

        this.barChartData = [
          {
            data: [ this.vegetablesMenu, this.vegetablesStandard, this.proteinMenu, this.proteinStandard, this.hydratesMenu, this.hydratesStandard, this.carboHydratesMenu, this.carboHydratesStandard, this.highInFatMenu, this.highInFatStandard],
            label: 'How healthy is your menu?',
            backgroundColor: ["red", "red", "blue", "blue", "brown", "brown", "yellow", "yellow", "green", "green"]
          }
        ];

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
