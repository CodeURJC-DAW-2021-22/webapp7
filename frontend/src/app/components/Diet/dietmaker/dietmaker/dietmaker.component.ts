import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Menu } from 'src/app/models/Menu/menu';
import { DietService } from 'src/app/services/Diet/diet.service';
import { MenuService } from 'src/app/services/Menu/menu.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-dietmaker',
  templateUrl: './dietmaker.component.html',
  styleUrls: ['./dietmaker.component.css']
})
export class DietmakerComponent implements OnInit {

  name: string;

  menusActive: Menu[] = [];
  menusDesactive: Menu[];
  menu: Menu;
  
  constructor(private router:Router, private menuservice:MenuService, private dietService: DietService) { }

  ngOnInit(): void {
    this.menuservice.getAllMenus().subscribe(
      response => {
        this.menusDesactive = response;
        console.log(this.menusDesactive)
      },
      error => console.error(error)
    );
  }

  activate(id: Number){
    this.menuservice.getMenu(id).subscribe(
      response => {
        this.menu = response;

        document.getElementById(this.menu.name)?.remove();
        this.menusActive.push(this.menu);
        this.menusDesactive.forEach((element,index)=>{
          if(element.id == this.menu.id) this.menusDesactive.splice(index,1);
       });
      },
      error => console.error(error)
    );
  }

  desactivate(id: Number){
    this.menuservice.getMenu(id).subscribe(
      response => {
        this.menu = response;

        document.getElementById(this.menu.name)?.remove();
        this.menusDesactive.push(this.menu);
        this.menusActive.forEach((element,index)=>{
          if(element.id == this.menu.id) this.menusActive.splice(index,1);
       });
      },
      error => console.error(error)
    );
  }

  save() {
    var menuFinal: number[] = [];
    for(let index of this.menusActive){
      menuFinal.push(index.id);
    }
    this.dietService.dietMaker(this.name, menuFinal).subscribe(
      (response: any) => this.router.navigate(['/user']),
      (error: any) => alert("Something gone wrong")
    );
  }
}
