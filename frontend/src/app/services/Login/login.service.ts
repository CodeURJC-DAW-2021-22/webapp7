import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users } from 'src/app/Class/Users/users';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  logIn(user: Users){
    return this.http.post<Users>("/api/auth/login", user)
  }
  logOut(){
    return this.http.get("/api/auth/logout")
  }
  refresh(user: Users){
    return this.http.post<Users>("/api/auth/refresh", user)
  }

}
