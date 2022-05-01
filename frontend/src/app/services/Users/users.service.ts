import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Menu } from 'src/app/models/Menu/menu';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { Users } from 'src/app/models/Users/users';
import { RecipesService } from '../Recipes/recipes.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  menu : Menu;
  constructor(private http: HttpClient, private recipeService:RecipesService) { }

  getMyProfile(){
    return this.http.get<Users>("api/users/me")
  }
  getUser(id: Number){
    return this.http.get<Users>("api/users/" + id)
  }
  register(user: Users){
    return this.http.post<Users>("api/users/new", user)
  }
  getNumberOfUser(){
    return this.http.get<Users[]>("api/users/all")
  }
  getUserRecipes(){
    return this.http.get<Recipes[]>("api/users/recipes")
  }
  getUserMenu(){
    return this.http.get<Menu>("api/users/menu")
  }
  getMenus(){
    return this.http.get<Menu[]>("/api/menus/")
  }
  getUserDiets(){
    return this.http.get("api/users/diets")
  }
}
