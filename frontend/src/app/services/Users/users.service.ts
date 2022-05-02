import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Diet } from 'src/app/models/Diet/diet';
import { Menu } from 'src/app/models/Menu/menu';
import { Recipes } from 'src/app/models/Recipes/recipes';
import { Users } from 'src/app/models/Users/users';
import { RecipesService } from '../Recipes/recipes.service';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private menu : Menu;

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

  addRecipe(id: number| undefined) {
    return this.http.put("api/users/recipe/" + id, {}, {}).subscribe(console.log)
  }

  activateMenu(id: number){
    return this.http.put("api/users/menu/" + id, {}, {}).subscribe(console.log)
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
    return this.http.get<Diet[]>("api/users/diets")
  }
}
