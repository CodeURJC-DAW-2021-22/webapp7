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


  recipes:Recipes[];

  constructor(private router:Router, private service:RecipesService) {
    this.service.getAllRecipes().subscribe(
      recipes => this.recipes = recipes,
      error => console.log(error)
    );
  }

  ngOnInit(): void {
    }

  recipeID(): void{
      this.router.navigate(['/']);
    }

  }

