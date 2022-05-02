import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipes } from './../../../models/Recipes/recipes';
import { RecipesService } from './../../../services/Recipes/recipes.service';

@Component({
  selector: 'app-recipeall',
  templateUrl: './recipeall.component.html',
  styleUrls: ['./recipeall.component.css']
})
export class RecipeallComponent implements OnInit {


  recipes: Recipes[];
  copy: Recipes[];
  page: number = 0;

  constructor(private router:Router, private recipeservice:RecipesService) { }

  ngOnInit(): void {
    this.page = 0
    this.recipeservice.getRecipes(this.page).subscribe(
      response => {
        this.recipes = response;
        this.copy = response;
        console.log(this.recipes)
      },
      error => console.error(error)
    );
  }

  load(){
    this.page++
    this.recipeservice.getRecipes(this.page).subscribe(
      response => {
        response.forEach(element =>{
          this.recipes.push(element);
        });
      },
      error => console.error(error)
    );
  }

  recipeID(id:number): void{
      this.router.navigate(['/recipespecific/:id']);
    }
  recipeImage(id:number){
      return this.recipes[id].recipeImage? '/api/recipes/'+id+'/image' : '/api/recipes/'+id+'/image';
  }
  }

