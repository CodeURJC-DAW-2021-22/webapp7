import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Menu } from '../models/menu.models';

const BASE_URL = '/api/menus/';

@Injectable({ providedIn: 'root' })
export class MenusService {

	constructor(private httpClient: HttpClient) { }

	getMenus(): Observable<Menu[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Menu[]>;
	}

	getMenu(id: number | string): Observable<Menu> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Menu>;
	}

	addMenu(menu: Menu) {

		if (!menu.id) {
			return this.httpClient.post(BASE_URL, menu)
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