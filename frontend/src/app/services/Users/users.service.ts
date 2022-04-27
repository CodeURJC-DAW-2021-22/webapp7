import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipes } from 'src/app/Class/Recipes/recipes';
import { Users } from 'src/app/Class/Users/users';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getMyProfile(){
    return this.http.get<Users>("api/users/me")
  }

  getUser(id: Number){
    return this.http.get<Users>("api/users/" + id)
  }

  register(user: Users){
    return this.http.post<Users>("api/users/new", user)
  }

  getUserRecipes(){
    return this.http.get<Recipes[]>("api/users/recipes")
  }

  getUserMenu(){
    return this.http.get("api/users/menu")
  }

  getUserDiets(){
    return this.http.get("api/users/diets")
  }
}
