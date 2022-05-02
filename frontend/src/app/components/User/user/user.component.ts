import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent{

  user: Users;

  constructor(private router: Router, private http: HttpClient, activatedRoute: ActivatedRoute,public usersService : UsersService, private loginService:LoginService) {
    this.user = loginService.currentUser();


   }

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

  logout(){
    this.loginService.logOut().subscribe(
      response =>{
        console.log(response);
        this.loginService.logged = false;
        this.router.navigate(["/home"]);
      },
      error => console.log(error)
    );
  }
  isLogged(){
    return this.loginService.isLogged();
  }
}
