import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { LoginService } from 'src/app/services/Login/login.service';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';

@Component({
  selector: 'app-recipeupdater',
  templateUrl: './recipeupdater.component.html',
  styleUrls: ['./recipeupdater.component.css']
})
export class RecipeupdaterComponent{

  
  difficulties = [
    { id: 1, name: "Easy" },
    { id: 2, name: "Medium" },
    { id: 3, name: "Hard" }
  ];

  oldRecipe: Recipes;

  id: number;

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

  constructor(private router: Router, activatedRoute: ActivatedRoute, private recipeService: RecipesService,private loginService: LoginService) {
    this.id = activatedRoute.snapshot.params['id'];
    recipeService.getRecipe(this.id).subscribe(
      response => {
        this.oldRecipe = response;

        this.vegetables = this.oldRecipe.vegetables;
        this.protein = this.oldRecipe.protein;
        this.hydrates = this.oldRecipe.hydrates;
        this.carbohydrates = this.oldRecipe.carbohydrates;
        this.highinfat = this.oldRecipe.highinfat;

        
        if(this.oldRecipe.difficulty.match("Fácil") || this.oldRecipe.difficulty.match("Easy")){
          this.difficulties.forEach((element,index)=>{
            if(element.id == 1) this.difficulties.splice(index,1);
          });
        }else if(this.oldRecipe.difficulty.match("Intermedia") || this.oldRecipe.difficulty.match("Medium")){
          this.difficulties.forEach((element,index)=>{
            if(element.id == 2) this.difficulties.splice(index,1);
          });
        }else{
          this.difficulties.forEach((element,index)=>{
            if(element.id == 3) this.difficulties.splice(index,1);
          });
        }
        this.difficulty = this.oldRecipe.difficulty;
      },
        error => console.error(error)
    );
  }


  cancel() {
    window.history.back();
  }

  save() {
    if(this.name == null){
      this.name = this.oldRecipe.name;
    }

    if(this.cookTime == null){
      this.cookTime = this.oldRecipe.cookTime;
    }

    if(this.preparation == null){
      this.preparation = this.oldRecipe.preparation;
    }

    if(this.ingredients == null){
      this.ingredients = this.oldRecipe.ingredients;
    }

    if(this.difficulty == null){
      this.difficulty = this.oldRecipe.difficulty;
    }

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

    this.recipeService.updateRecipe(this.id, this.name, this.cookTime, this.difficulty, this.preparation, this.ingredients, this.boolean).subscribe(
      (response: any) => this.router.navigate(['/recipeall']),
      (error: any) => alert("Something gone wrong")
  );
  }
    isAdmin(){
      return this.loginService.isAdmin();
    }
}
