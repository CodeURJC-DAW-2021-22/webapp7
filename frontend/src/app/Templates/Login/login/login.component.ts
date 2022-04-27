import { Users } from 'src/app/Class/Users/users';
import { LoginService } from './../../../services/Login/login.service';
import { Component, OnInit } from '@angular/core';
import { UsersService } from 'src/app/services/Users/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Users;

  constructor(public loginService : LoginService, public usersService : UsersService) { }

  logIn(event: any, name: string, pass: string) {

    event.preventDefault();

    /*name= this.user.name;
    pass= this.user.password;*/

    this.loginService.logIn(name, pass);
    //this.loginService.logIn(this.user);
  }
  register(event: any, name: string, mail: string, pass: string){
    event.preventDefault();


    /*name= this.user.name;
    mail= this.user.mail;
    pass= this.user.password;*/

    this.loginService.register(name, mail, pass);
    //this.loginService.register(this.user);
  }


  ngOnInit(): void {
  }

}
