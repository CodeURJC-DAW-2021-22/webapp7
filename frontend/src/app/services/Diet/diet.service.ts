import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Diet } from '../../models/Diet/diet';
import { Menu } from '../../models/Menu/menu';

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

  dietMaker(name: string, menuList: number[]){
    let data ={name: name, menuList: menuList};
    return this.http.post("/api/diets/new", {}, {params: data})

  }

  dietEditor(id: Number){
    return this.http.get("/api/diets/menu" + id)
  }
}
