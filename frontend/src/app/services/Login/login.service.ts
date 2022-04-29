import { User } from './../../models/user.models';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { pipe } from 'rxjs';
import { Recipes } from './../../Class/Recipes/recipes';
import { Users } from './../../Class/Users/users';
import { RecipesService } from '../Recipes/recipes.service';
import { UsersService } from '../Users/users.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }


  logged: boolean;
  user: Users ;
	httpClient: any;
  recipes: Recipes[];
  recipe: Recipes;

  logIn(username : String, password: String){
    //return this.http.post<Users>("/api/auth/login",  /*accessToken, refreshToken,*/ {name, password},{withCredentials:true});
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
  refresh(user: Users){
    return this.http.post<Users>("/api/auth/refresh", user)
  }
  register(name : String, mail : String ,password: String){
    return this.http.post<Users>("api/users/new", {name, password, mail})
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

isAdmin() {
  return this.user && this.user.admin;
}

currentUser() {
  return this.user;
}
}
