import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Recipes } from 'src/app/Class/Recipes/recipes';
import { LoginService } from 'src/app/services/Login/login.service';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';

@Component({
  selector: 'app-recipespecific',
  templateUrl: './recipespecific.component.html',
  styleUrls: ['./recipespecific.component.css']
})
export class RecipespecificComponent{

  recipe: Recipes;

  constructor(private router:Router,activatedRoute: ActivatedRoute, private service:RecipesService, public loginService: LoginService) {
    const id = activatedRoute.snapshot.params['id'];
    service.getRecipe(id).subscribe(
      response => {
        this.recipe = response;
      },
        error => console.error(error)
    );

   }

}
