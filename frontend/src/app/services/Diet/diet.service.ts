import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DietService {

  constructor(private http: HttpClient) { }

  getAllDiets(){
    return this.http.get("/api/diets/")
  }

  getDiet(id: Number){
    return this.http.get("/api/diets" + id)
  }

  dietMaker(){
    return this.http.get("/api/diets/new")
  }

  dietEditor(id: Number){
    return this.http.get("/api/diets/menu" + id)
  }
}
