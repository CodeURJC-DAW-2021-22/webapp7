import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diet } from 'src/app/models/Diet/diet';
import { Menu } from 'src/app/models/Menu/menu';
import { DietService } from 'src/app/services/Diet/diet.service';
import { LoginService } from 'src/app/services/Login/login.service';


@Component({
  selector: 'app-dietloader',
  templateUrl: './dietloader.component.html',
  styleUrls: ['./dietloader.component.css']
})
export class DietloaderComponent {


  menus: Menu[];
  diet: Diet;

  constructor(private loginService:LoginService, private router:Router, activatedRoute: ActivatedRoute, private dietservice:DietService) {
    const id = activatedRoute.snapshot.params['id'];

    dietservice.getDiet(id).subscribe(
      response => {
        this.diet = response;
        this.menus = this.diet.menus;
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
