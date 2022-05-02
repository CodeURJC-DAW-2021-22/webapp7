import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from './../../../models/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { UsersService } from './../../../services/Users/users.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  admin: Users;
  userList: Users[] = [];
  healthyUsers: number=0;
  total: number;
  unhealthyUsers: number=0;
  constructor(private router: Router, private http: HttpClient, private loginService: LoginService, private userService:UsersService){
    this.admin = loginService.currentUser();
    this.userList = this.userService.getUsers();
    this.total = this.userList.length;
    for(var i = 0; i< this.total; i++){
        if(this.userList[i].activeMenu.healthy==true){
          this.increaseHealthy();
        }
        else{
          this.increaseUnhealthy();
        }
    }
    }
  increaseHealthy(){
    this.healthyUsers = this.healthyUsers + 1;
  }
  increaseUnhealthy(){
    this.unhealthyUsers = this.unhealthyUsers + 1;
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
    isAdmin(){
      return this.loginService.isAdmin();
    }
}
