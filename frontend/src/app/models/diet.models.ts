import { Menu } from './menu.models';
/*import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})*/
export interface Diet {
  id?: number;
  name: string;
  dieta: Menu[]
}
