import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { User } from '../models/user.models';
import { Recipe } from '../models/recipe.models';
import { Menu } from '../models/menu.models';


const BASE_URL = '/api/auth/';

@Injectable({ providedIn: 'root' })
export class UsersService{

	logged: any ;
  user: any ;
	httpClient: any;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        this.http.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = response as User;
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }

    logIn(user: string, pass: string) {

        this.http.post(BASE_URL + "/login", { username: user, password: pass }, { withCredentials: true })
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
                this.user = undefined;
            });

    }

    isLogged() {
        return this.logged;
    }

    isAdmin() {
        return this.user && this.user.admin;
    }

    currentUser() {
        return this.user;
    }

	getStoredRecipes(): Observable<Recipe[]> {

		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Recipe[]>;
	}

	addRecipe(recipe: Recipe) {

		if (!this.user.storedRecipes.includes(recipe)) {
			return this.httpClient.post(BASE_URL, recipe)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + recipe.id, recipe).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	deleteRecipe(recipe: Recipe) {
		if (this.user.storedRecipes.includes(recipe)) {
			return this.httpClient.delete(BASE_URL + recipe.id).pipe(
				catchError(error => this.handleError(error))
			);
		} else {
			return this.httpClient.put(BASE_URL + recipe.id, recipe).pipe(
				catchError(error => this.handleError(error))
			);
		}

	}

	getActiveMenu(): Observable<Menu> {
		return this.httpClient.get(BASE_URL + this.user.activeMenu.id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Menu>;
	}

	setActiveMenu(menu: Menu){
		if (!(JSON.stringify(this.user.activeMenu.id) == JSON.stringify(menu.id))) {
			return this.httpClient.set(BASE_URL, menu)
				.pipe(
					catchError(error => this.handleError(error))
				);
		} else {
			return this.httpClient.put(BASE_URL + menu.id, menu).pipe(
				catchError(error => this.handleError(error))
			);
		}
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}
