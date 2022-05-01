import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from 'src/app/models/Users/users';
import { LoginService } from 'src/app/services/Login/login.service';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  admin: Users;
  Users: Users[];
  healthyUsers: number;
  unhealthyUsers: number;
  constructor(private router: Router, private http: HttpClient, private loginService: LoginService, private userService:UsersService) {

    this.admin = loginService.currentUser();

    this.userService.getNumberOfUser().subscribe(
      response => {
        this.Users = response;
      },
        error => console.error(error)
    );
      var total = Users.length;
      this.healthyUsers = 0;
      this.unhealthyUsers = 0;
      for(let i = 0; i < total;i++){
        if (this.Users[i].isHealthy()){
          this.healthyUsers = this.healthyUsers + 1;
        }
        else{
          this.unhealthyUsers = this.unhealthyUsers + 1;
        }
      }
    }

  logOut(){
    this.loginService.logOut().subscribe(
      response => {
        console.log(response);
        this.loginService.logged = false;
        this.router.navigate(["/home"]);
      },
      error => console.log(error)
    );
  }
}
