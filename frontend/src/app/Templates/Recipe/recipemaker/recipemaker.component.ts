import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { RecipesService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipemaker',
  templateUrl: './recipemaker.component.html',
  styleUrls: ['./recipemaker.component.css']
})
export class RecipemakerComponent{

  difficulties = [
    { id: 1, name: "Easy" },
    { id: 2, name: "Medium" },
    { id: 3, name: "Hard" }
  ];

  name: string;
  cookTime: number;
  difficulty: string;
  ingredients: string;
  preparation: string;
  vegetables: boolean;
  protein: boolean;
  hydrates: boolean;
  carbohydrates: boolean;
  highinfat: boolean;

  boolean: string[] = [];

  constructor(private router: Router, private service: RecipesService) {}


  cancel() {
    window.history.back();
  }

  save() {
    const cookTimeString = this.cookTime.toString();

    if(this.vegetables){
      this.boolean.push("vegetables");
    }
    if(this.protein){
      this.boolean.push("protein");
    }
    if(this.hydrates){
      this.boolean.push("hydrates");
    }
    if(this.carbohydrates){
      this.boolean.push("carbohydrates");
    }
    if(this.highinfat){
      this.boolean.push("highinfat");
    }

    const formData = new FormData();
    formData.append('name', this.name);
    formData.append('time', cookTimeString);
    formData.append('difficulty', this.difficulty);
    formData.append('preparation', this.preparation);
    formData.append('ingredients', this.ingredients);
    formData.append('booleanos', this.boolean.toString());

    this.service.addRecipe(formData).subscribe(
      response => this.router.navigate(['/']),
      error => alert("Something gone wrong")
  );
  }

}
