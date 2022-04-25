import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipes } from 'src/app/Class/Recipes/recipes';
import { Recipe } from 'src/app/models/recipe.models';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  private recipe: Recipes = new Recipes;

  constructor(private http: HttpClient) { }

  getAllRecipes(){
    return this.http.get<Recipes[]>("/api/recipes/")
  }

  getRecipe(id: Number){
    return this.http.get("/api/recipes/" + id)
  }

  getRecipes(page: Number){
    return this.http.get("/api/recipes/page" + page)
  }

  deleteRecipe(id: Number){
    return this.http.delete("/api/recipes/" + id)
  }

  createRecipe(){
    return this.http.post("/api/recipes/new", {})
  }

  updateRecipe(id: Number, recipe: Recipes){
    return this.http.put("/api/recipes/" + id, recipe)
  }

  uploadImage(id: Number, image: FormData){
    return this.http.post("/api/recipes/" + id + "/image", image)
  }

  downloadImage(id: Number){
    return this.http.get("/api/recipes/" + id + "/image")
  }
}
