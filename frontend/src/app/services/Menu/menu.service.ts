import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Menu } from '../../models/Menu/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) { }

  getAllMenus(){
    return this.http.get<Menu[]>("/api/menus/")
  }

  getMenu(id: Number){
    return this.http.get<Menu>("/api/menus/" + id)
  }

  menuMaker(name: string, lunchMonday: number, lunchTuesday: number, lunchWednesday: number, lunchThursday: number, lunchFriday: number, lunchSaturday: number, lunchSunday: number, dinnerMonday: number, dinnerTuesday: number, dinnerWednesday: number, dinnerThursday: number, dinnerFriday: number, dinnerSaturday: number, dinnerSunday: number){
    let data ={name: name, lunchMonday: lunchMonday, lunchTuesday: lunchTuesday, lunchWednesday: lunchWednesday, lunchThursday: lunchThursday, lunchFriday: lunchFriday, lunchSaturday: lunchSaturday, lunchSunday: lunchSunday, dinnerMonday: dinnerMonday, dinnerTuesday: dinnerTuesday, dinnerWednesday:dinnerWednesday, dinnerThursday: dinnerThursday, dinnerFriday: dinnerFriday, dinnerSaturday: dinnerSaturday, dinnerSunday: dinnerSunday};
    return this.http.post("/api/menus/new", {}, {params: data})
  }
}
