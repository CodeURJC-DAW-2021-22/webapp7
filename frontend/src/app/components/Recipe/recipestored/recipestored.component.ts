import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-recipestored',
  templateUrl: './recipestored.component.html',
  styleUrls: ['./recipestored.component.css']
})
export class RecipestoredComponent implements OnInit {

  recipes: Recipes[];
  copy: Recipes[];

  constructor(private router:Router,public usersService : UsersService, private recipeservice:RecipesService) { }

  ngOnInit(): void {
    this.usersService.getUserRecipes().subscribe(
      response => {
        this.recipes = response;
        this.copy = response;
        console.log(this.recipes)
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
