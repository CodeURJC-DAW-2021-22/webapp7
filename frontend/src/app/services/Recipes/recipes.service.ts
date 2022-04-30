import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipes } from 'src/app/models/Recipes/recipes';

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
    return this.http.get<Recipes>("/api/recipes/" + id)
  }

  getRecipes(pageInt: number){
    let data = {page: pageInt};
    return this.http.get<Recipes[]>("/api/recipes/", {params: data})
  }

  deleteRecipe(id: Number){
    return this.http.delete("/api/recipes/" + id)
  }

  createRecipe(name: string, time: number, difficulty: string, preparation: string, ingredients: string, booleanos: string[]){
    let data ={"name": name, time: time, difficulty: difficulty, preparation: preparation, ingredients: ingredients, booleanos: booleanos};
    return this.http.post("/api/recipes/new", {},{params: data})
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
