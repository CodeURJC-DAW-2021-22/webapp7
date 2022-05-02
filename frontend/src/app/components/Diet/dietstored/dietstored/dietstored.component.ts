import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diet } from './../../../../models/Diet/diet';
import { Users } from './../../../../models/Users/users';
import { LoginService } from './../../../../services/Login/login.service';
import { UsersService } from './../../../../services/Users/users.service';

@Component({
  selector: 'app-dietstored',
  templateUrl: './dietstored.component.html',
  styleUrls: ['./dietstored.component.css']
})
export class DietstoredComponent {

  diets: Diet[];
  copy: Diet[];
  user: Users;

  constructor(private router:Router,activatedRoute: ActivatedRoute, private usersService: UsersService,private loginService: LoginService) {

    this.diets = loginService.currentUser().storedDiets;
  }

  copyDiets(diet: Diet[]){
    this.copy = diet;
  }

  dietID(id:number): void{
    this.router.navigate(['/dietloader/:id']);
  }
  isLogged(){
    return this.loginService.isLogged();
  }


}
