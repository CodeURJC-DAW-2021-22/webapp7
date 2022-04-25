import { Recipe } from './recipe.models';
/*import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export interface Menu {
  id?: number;
  name:string;
  weeklyPlan: Recipe[]
}
