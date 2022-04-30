import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Menu } from 'src/app/models/Menu/menu';
import { MenuService } from 'src/app/services/Menu/menu.service';

@Component({
  selector: 'app-menuall',
  templateUrl: './menuall.component.html',
  styleUrls: ['./menuall.component.css']
})
export class MenuallComponent implements OnInit {

  menus: Menu[];
  copy: Menu[];
  
  constructor(private router:Router, private menuservice:MenuService) { }

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

}
