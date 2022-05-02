import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipes } from './../../../models/Recipes/recipes';
import { LoginService } from './../../../services/Login/login.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-recipestored',
  templateUrl: './recipestored.component.html',
  styleUrls: ['./recipestored.component.css']
})
export class RecipestoredComponent implements OnInit {

  recipes: Recipes[];
  copy: Recipes[];

  constructor(private router:Router,public usersService : UsersService, private recipeservice:RecipesService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.recipes = this.loginService.currentUser().storedRecipes;
    this.copy = this.loginService.currentUser().storedRecipes;
  }

  recipeID(id:number): void{
      this.router.navigate(['/recipespecific/:id']);
    }
  recipeImage(id:number){
      return this.recipes[id].recipeImage? '/api/recipes/'+id+'/image' : '/api/recipes/'+id+'/image';
  }

  isLogged(){
    return this.loginService.isLogged();
  }
}
