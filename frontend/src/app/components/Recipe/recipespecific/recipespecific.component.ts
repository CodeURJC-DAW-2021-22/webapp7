import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Recipes } from './../../../models/Recipes/recipes';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-recipespecific',
  templateUrl: './recipespecific.component.html',
  styleUrls: ['./recipespecific.component.css']
})
export class RecipespecificComponent{

  recipe: Recipes;
  user: Users;
  stored: boolean;
  id: number;

  constructor(private router:Router,activatedRoute: ActivatedRoute, private recipeService:RecipesService, public loginService: LoginService, private userService: UsersService) {
    this.id = activatedRoute.snapshot.params['id'];
    this.user = loginService.currentUser();
    //hasta que no esté hecho el login no va a funcionar
    userService.getUserRecipes().subscribe(
      response => {
        loginService.user.storedRecipes.push(this.recipe)
      },
      error => console.error(error)
    );

    recipeService.getRecipe(this.id).subscribe(
      response => {
        this.recipe = response;
        this.recipeIsStored();
      },
        error => console.error(error)
    );
   }


   deleteRecipe(){
    this.recipeService.deleteRecipe(this.id).subscribe(
      (response: any) => this.router.navigate(['/recipeall']),
      (error: any) => alert("Something gone wrong")
    );
   }

   storeRecipe(){
    this.userService.addRecipe(this.recipe.id);
    this.stored = true;
   }

   recipeIsStored(){
    var storeRecipes = this.user.storedRecipes;
    this.stored = false;
    for(let index of storeRecipes){
      if(index.id == this.recipe.id){
        this.stored = true;
      }
    }
   }
   hasVegetables(){
     return this.recipe.vegetables;
   }
   hasProteyn(){
     return this.recipe.protein;
   }
   hasCarboHydrates(){
     return this.recipe.carbohydrates;
   }
   isHighInFat(){
     return this.recipe.highinfat;
   }
   hasHydrates(){
     return this.recipe.hydrates;
   }

   recipeID(id:number): void{
    this.router.navigate(['/recipeupdater/:id']);
  }

   downloadImage(){
    return  this.recipeService.downloadImage(this.recipe);
   }

}
