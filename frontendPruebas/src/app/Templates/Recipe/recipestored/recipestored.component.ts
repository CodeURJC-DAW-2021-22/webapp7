import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { Recipe } from './../../models/recipe.model';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-recipestored',
  templateUrl: './recipestored.component.html',
  styleUrls: ['./recipestored.component.css']
})
export class RecipestoredComponent implements OnInit {
  
  recipes: Recipe[];

  constructor(private router: Router, private service: UserService, public loginService: LoginService) { }

  ngOnInit(): void {
    this.service.getStoredRecipes().subscribe(
      recipes => this.recipes = recipes,
      error => console.log(error)
    );
  }

}
