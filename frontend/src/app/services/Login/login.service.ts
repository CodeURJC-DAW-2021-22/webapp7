import { Users } from '../../models/Users/users';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recipes } from '../../models/Recipes/recipes';
import { RecipesService } from '../Recipes/recipes.service';
import { UsersService } from '../Users/users.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  logged: boolean = false;
  user: Users;
	httpClient: any;
  recipes: Recipes[];
  recipe: Recipes;

  constructor(private router:Router,private http: HttpClient,private userService: UsersService) {
    this.refresh();
    this.userService.getMyProfile().subscribe(
      response=>{
        this.user = response as Users;
        this.logged = true;
      }
    );

  }

  register(username : String, mail : String ,password: String){
    this.http.post("api/users/new",{username, password, mail} , {withCredentials:true}).subscribe(
      (response) => this.router.navigate(['/login']),
      (error) => alert("Wrong Credentials")
    );
  }


  logIn(username : String, password: String){
    this.http.post("/api/auth/login", {username,password},{withCredentials:true}).subscribe(
      (response) => this.reqIsLogged(),
      (error) => alert("Wrong credentials")
    );
  }
  reqIsLogged() {

    this.http.get('/api/users/me', { withCredentials: true }).subscribe(
        response => {
            this.user = response as Users;
            this.logged = true;
        },
        error => {
            if (error.status != 404) {
                console.error('Error when asking if logged: ' + JSON.stringify(error));
            }
        }
    );

}

  logOut(){
    return this.http.get("/api/auth/logout")
  }
  refresh(){
    return this.http.post<Users>("/api/auth/refresh",{withCredentials:true})
  }

  /*constructor(private http: HttpClient, private userService: UsersService, private recipeService:RecipesService) {
    this.reqIsLogged();
}



logIn(name: string, pass: string) {

    this.http.post(BASE_URL + "/login", { username: name, password: pass }, { withCredentials: true })
        .subscribe(
            (response) => this.reqIsLogged(),
            (error) => alert("Wrong credentials")
        );

}

logOut() {

    return this.http.post(BASE_URL + '/logout', { withCredentials: true })
        .subscribe((resp: any) => {
            console.log("LOGOUT: Successfully");
            this.logged = false;
            this.user = <Users>{};
        });

}
register(name: string, mail: string, pass: string){
  this.http.post("api/users/new", {username: name, email : mail, password: pass},{withCredentials: true})
  .subscribe(
    (response) => this.reqIsLogged(),
    (error) =>alert("Could not create a user")
  );
}*/
isLogged() {
  return this.logged;
}

isAdmin(){
if (this.isLogged()==true){
  if (this.user.admin==true){
    return true;
  }
}
return false;
}

currentUser() {
  return this.user;
}
}
