import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Recipe } from '../models/recipe.models';

const BASE_URL = '/api/recipes/';

@Injectable({ providedIn: 'root' })
export class RecipesService {

	constructor(private httpClient: HttpClient) { }

	getRecipes(): Observable<Recipe[]> {
		return this.httpClient.get(BASE_URL).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Recipe[]>;
	}

	getRecipe(id: number | string): Observable<Recipe> {
		return this.httpClient.get(BASE_URL + id).pipe(
			catchError(error => this.handleError(error))
		) as Observable<Recipe>;
	}

	addRecipe(recipe: Recipe) {

		if (!recipe.id) {
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

	setRecipeImage(recipe: Recipe, formData: FormData) {
		return this.httpClient.post(BASE_URL + recipe.id + '/image', formData)
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteRecipeImage(recipe: Recipe) {
		return this.httpClient.delete(BASE_URL + recipe.id + '/image')
			.pipe(
				catchError(error => this.handleError(error))
			);
	}

	deleteRecipe(recipe: Recipe) {
		return this.httpClient.delete(BASE_URL + recipe.id).pipe(
			catchError(error => this.handleError(error))
		);
	}

	updateRecipe(recipe: Recipe) {
		return this.httpClient.put(BASE_URL + recipe.id, recipe).pipe(
			catchError(error => this.handleError(error))
		);
	}

	private handleError(error: any) {
		console.log("ERROR:");
		console.error(error);
		return throwError("Server error (" + error.status + "): " + error.text())
	}
}