import { UsersService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router: Router, activatedRoute: ActivatedRoute,public usersService : UsersService) { }

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
    this.router.navigate(['/home'])
  }

  ngOnInit(): void {
  }

}
