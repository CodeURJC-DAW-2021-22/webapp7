import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Menu } from 'src/app/Class/Menu/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getAllMenus(){
    return this.http.get("/api/menus/")
  }

  getMenu(id: Number){
    return this.http.get("/api/menus" + id)
  }

  menuMaker(menu: Menu){
    return this.http.post("/api/menus/new", menu)
  }
}
