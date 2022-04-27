import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users } from 'src/app/Class/Users/users';

const BASE_URL = '/api/auth/';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //constructor(private http: HttpClient) { }


  logged: boolean;
  users: Users ;
	httpClient: any;

  /*logIn(user: Users){
    return this.http.post<Users>("/api/auth/login", user)
  }
  logOut(){
    return this.http.get("/api/auth/logout")
  }
  refresh(user: Users){
    return this.http.post<Users>("/api/auth/refresh", user)
  }
  register(user: Users){
    return this.http.post<Users>("api/users/new", user)
  }*/

  constructor(private http: HttpClient) {
    this.reqIsLogged();
}

reqIsLogged() {

    this.http.get('/api/users/me', { withCredentials: true }).subscribe(
        response => {
            this.users = response as Users;
            this.logged = true;
        },
        error => {
            if (error.status != 404) {
                console.error('Error when asking if logged: ' + JSON.stringify(error));
            }
        }
    );

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
            this.users = <Users>{};
        });

}
register(name: string, mail: string, pass: string){
  this.http.post("api/users/new", {username: name, email : mail, password: pass},{withCredentials: true})
  .subscribe(
    (response) => this.reqIsLogged(),
    (error) =>alert("Could not create a user")
  );
}

}
