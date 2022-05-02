import { Component, OnInit } from '@angular/core';
import { Recipes } from './../../../models/Recipes/recipes';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { RecipesService } from './../../../services/Recipes/recipes.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recipe1: Recipes;
  recipe2: Recipes;
  recipeWeekly: Recipes;
  rnd: number;
  tryRecipe1: Recipes;
  tryRecipe2: Recipes;
  carrouselRecipes1: Recipes[];
  copy1 : Recipes[];
  carrouselRecipes2: Recipes[];
  copy2: Recipes[];
  user: Users;
  constructor(private service:RecipesService,loginService: LoginService) {
    this.user = loginService.currentUser();
    service.getRecipe(20).subscribe(
      response => {
        this.recipe1 = response;
      },
        error => console.error(error)
    );

    this.rnd = Math.random() * (23 - 1) + 1;
    this.rnd = Math.ceil(this.rnd);
    service.getRecipe(this.rnd).subscribe(
      response => {
        this.recipeWeekly = response;
      },
        error => console.error(error)
    );
    service.getRecipe(22).subscribe(
      response => {
        this.recipe2 = response;
      },
        error => console.error(error)
    );
    this.rnd = Math.random() * (23 - 1) + 1;
    this.rnd = Math.ceil(this.rnd);
    service.getRecipe(this.rnd).subscribe(
      response => {
        this.tryRecipe1 = response;
      },
        error => console.error(error)
    );
    this.rnd = Math.random() * (23 - 1) + 1;
    this.rnd = Math.ceil(this.rnd);
    service.getRecipe(this.rnd).subscribe(
      response => {
        this.tryRecipe2 = response;
      },
        error => console.error(error)
    );


    this.service.getRecipes(0).subscribe(
      response => {
        this.carrouselRecipes1 = response;
        this.copy1 = response;
        for (var i=0;i<=8;i++){
          this.carrouselRecipes1.pop();
        }
      },
      error => console.error(error)
    );

    this.service.getRecipes(1).subscribe(
      response => {
        this.carrouselRecipes2 = response;
        this.copy1 = response;
        for (var i=0;i<=8;i++){
          this.carrouselRecipes2.pop();
        }
      },
      error => console.error(error)
    );
  }

  ngOnInit(): void {
  }

}
