import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipes } from 'src/app/Class/Recipes/recipes';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';

@Component({
  selector: 'app-recipeall',
  templateUrl: './recipeall.component.html',
  styleUrls: ['./recipeall.component.css']
})
export class RecipeallComponent implements OnInit {


  recipes: Recipes[];
  copy: Recipes[];
  page: Number = 0;

  constructor(private router:Router, private recipeservice:RecipesService) { }

  ngOnInit(): void {
    this.page = 0;
    this.recipeservice.getRecipes(this.page).subscribe(
      response => {
        this.recipes = response;
        this.copy = response;
      },
      error => console.error(error)
    );
  }

  recipeID(): void{
      this.router.navigate(['/']);
    }

  }

