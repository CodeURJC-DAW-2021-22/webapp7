import { Diet } from './diet.models';
import { Recipe } from './recipe.models';
import { Menu } from './menu.models';
/*import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export interface User {
  id?:number;
  name:string;
  password:string;
  mail:string;
  admin:boolean;

  activeMenu: Menu;
  storedRecipes: Recipe[]
  storedDiets: Diet[]
  }
