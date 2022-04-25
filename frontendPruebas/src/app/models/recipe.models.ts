import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})

export interface Recipe {
  id?: number;
  name: string;
  cookTime: number;
  difficulty: string;
  uploadDate: Date; //habrá que mirar como de bien está
  ingredients: string;
  creator: string;

  hasPhoto: boolean;
  recipeImage: Blob;

  vegetbales: boolean;
  protein: boolean;
  hydrates: boolean;
  carbohydrates: boolean;
  highinfat: boolean;

 }
