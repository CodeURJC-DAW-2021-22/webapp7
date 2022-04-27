import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
    this.recipeservice.getAllRecipes().subscribe(
      response => {
        this.recipes = response;
        this.copy = response;
        console.log(this.recipes)
      },
      error => console.error(error)
    );
  }

  recipeID(): void{
      this.router.navigate(['/']);
    }
  recipeImage(id:number){
      return this.recipes[id].recipeImage? '/api/recipes/'+id+'/image' : '/api/recipes/'+id+'/image';
  }
  }

