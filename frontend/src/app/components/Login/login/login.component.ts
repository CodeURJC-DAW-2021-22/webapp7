import { Users } from './../../../models/Users/users';
import { LoginService } from '././../../../services/Login/login.service';
import { Component, OnInit } from '@angular/core';
import { UsersService } from './../../../services/Users/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Users;

  constructor(private router:Router,public loginService : LoginService, public usersService : UsersService) {
    this.user = loginService.currentUser();
  }

  logIn(event: any, name: String, pass: String) {


    event.preventDefault();

    this.loginService.logIn(name,pass);
      if (this.loginService.isLogged()){
        this.router.navigate(['/home']);
      }
      else{
        this.router.navigate(['/login']);
      }
  }

  register(event: any, name: String, mail: String, pass: String){

    event.preventDefault();
    this.loginService.register(name, mail, pass);
  }


  ngOnInit(): void {
  }

}
