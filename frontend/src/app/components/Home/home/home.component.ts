import { Component, OnInit } from '@angular/core';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { Users } from 'src/app/models/Users/users';
import { LoginService } from 'src/app/services/Login/login.service';
import { RecipesService } from 'src/app/services/Recipes/recipes.service';

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
  }

  ngOnInit(): void {
  }

}
