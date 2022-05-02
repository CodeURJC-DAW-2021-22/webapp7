import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './../../../services/Login/login.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';

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

  constructor(private router: Router, private service: RecipesService,private loginService: LoginService) {}


  cancel() {
    window.history.back();
  }

  save() {

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

    if(this.difficulty == "1"){
      this.difficulty = "Easy";
    }else if(this.difficulty == "2"){
      this.difficulty = "Medium";
    }else{
      this.difficulty = "Hard";
    }

    this.service.createRecipe(this.name, this.cookTime, this.difficulty, this.preparation, this.ingredients, this.boolean).subscribe(
      (response: any) => this.router.navigate(['/recipeall']),
      (error: any) => alert("Something gone wrong")
  );
  }
    isAdmin(){
      return this.loginService.isAdmin();
    }

}
