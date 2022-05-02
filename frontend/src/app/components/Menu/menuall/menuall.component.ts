import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Menu } from './../../../models/Menu/menu';
import { LoginService } from './../../../services/Login/login.service';
import { MenuService } from './../../../services/Menu/menu.service';

@Component({
  selector: 'app-menuall',
  templateUrl: './menuall.component.html',
  styleUrls: ['./menuall.component.css']
})
export class MenuallComponent implements OnInit {

  menus: Menu[];
  copy: Menu[];

  constructor(private router:Router, private menuservice:MenuService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.menuservice.getAllMenus().subscribe(
      response => {
        this.menus = response;
        this.copy = response;
        console.log(this.menus)
      },
      error => console.error(error)
    );
  }

  menuID(id:number): void{
    this.router.navigate(['/menuloader/:id']);
  }
  isLogged(){
    return this.loginService.isLogged();
  }

}
