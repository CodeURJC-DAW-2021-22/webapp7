import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/Login/login.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent{

  constructor(private router: Router, activatedRoute: ActivatedRoute,public usersService : UsersService, private loginService:LoginService) { }

  goToActiveMenu(){
    this.router.navigate(['/menuactive'])
  }

  goToDietMaker(){
    this.router.navigate(['/dietmaker'])
  }

  goToStoredDiets(){
    this.router.navigate(['/dietstored'])
  }

  goToMenuMaker(){
    this.router.navigate(['/menumaker'])
  }

  goToMenuAll(){
    this.router.navigate(['/menuall'])
  }

  goToStoredRecipes(){
    this.router.navigate(['/recipestored'])
  }

  logOut(){

  }
}