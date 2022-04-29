import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Recipes } from 'src/app/Class/Recipes/recipes';
import { Users } from 'src/app/Class/Users/users';
import { LoginService } from 'src/app/services/Login/login.service';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-recipespecific',
  templateUrl: './recipespecific.component.html',
  styleUrls: ['./recipespecific.component.css']
})
export class RecipespecificComponent{

  recipe: Recipes;
  user: Users;

  constructor(private router:Router,activatedRoute: ActivatedRoute, private service:RecipesService, public loginService: LoginService, private userService: UsersService) {
    const id = activatedRoute.snapshot.params['id'];
    this.user = loginService.currentUser();
    //hasta que no esté hecho el login no va a funcionar
    userService.getUserRecipes().subscribe(
      response => {
        this.user.storedRecipes = response;
      },
      error => console.error(error)
    );

    service.getRecipe(id).subscribe(
      response => {
        this.recipe = response;
      },
        error => console.error(error)
    );

   }
   storeRecipe(id:number){
     this.user.storedRecipes.push(this.recipe);
   }

   recipeIsStored(){
     return this.user.storedRecipes.indexOf(this.recipe) != -1;
   }
   hasVegetables(){
     return this.recipe.vegetbales;
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

}
