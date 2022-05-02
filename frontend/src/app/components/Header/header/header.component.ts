import { UsersService } from '../../../services/Users/users.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../../services/Login/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  constructor(private router: Router, activatedRoute: ActivatedRoute,public usersService : UsersService, private loginService:LoginService) { }
  isLogged(){
    return this.loginService.isLogged();
  }
  isAdmin(){
    return this.loginService.isAdmin();
  }
  goToHome(){
    this.router.navigate(['/home'])
  }
  goToRecipes(){
    this.router.navigate(['/recipeall'])
  }
  goToAboutUs(){
    this.router.navigate(['/aboutus'])
  }
  goToUser(){
    this.router.navigate(['/user'])
  }
  goToAdmin(){
    this.router.navigate(['/admin'])
  }
  goToLogin(){
    this.router.navigate(['/login'])
  }
  goToLogout(){
    this.router.navigate(['/user'])
  }

  ngOnInit(): void {
  }

}
