import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Diet } from 'src/app/models/Diet/diet';

@Injectable({
  providedIn: 'root'
})
export class DietService {

  constructor(private http: HttpClient) { }

  getAllDiets(){
    return this.http.get<Diet[]>("/api/diets/")
  }

  getDiet(id: Number){
    return this.http.get<Diet>("/api/diets/" + id)
  }

  dietMaker(){
    return this.http.get("/api/diets/new")
  }

  dietEditor(id: Number){
    return this.http.get("/api/diets/menu" + id)
  }
}
