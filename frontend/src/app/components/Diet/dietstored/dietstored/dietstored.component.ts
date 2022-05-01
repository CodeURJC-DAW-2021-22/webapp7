import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Diet } from 'src/app/models/Diet/diet';
import { Users } from 'src/app/models/Users/users';
import { DietService } from 'src/app/services/Diet/diet.service';
import { LoginService } from 'src/app/services/Login/login.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-dietstored',
  templateUrl: './dietstored.component.html',
  styleUrls: ['./dietstored.component.css']
})
export class DietstoredComponent {

  diets: Diet[];
  copy: Diet[];
  user: Users;

  constructor(private router:Router,activatedRoute: ActivatedRoute, private usersService: UsersService) {
    usersService.getUserDiets().subscribe(
      response => {
        this.diets = response;
        this.copyDiets(this.diets);
      },
        error => console.error(error)
    );
  }

  copyDiets(diet: Diet[]){
    this.copy = diet;
  }

  dietID(id:number): void{
    this.router.navigate(['/dietloader/:id']);
  }


}
