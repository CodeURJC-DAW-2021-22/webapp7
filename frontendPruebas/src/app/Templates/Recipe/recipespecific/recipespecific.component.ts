import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Recipe } from 'src/app/models/recipe.models';
import { RecipesService } from 'src/app/services/recipe.service';
import { Router, ActivatedRoute } from '@angular/router';


const BASE_URL = 'https://127.0.0.1:8443/api/';

@Component({
  selector: 'app-recipespecific',
  templateUrl: './recipespecific.component.html',
  styleUrls: ['./recipespecific.component.css']
})
export class RecipespecificComponent implements OnInit {

  recipe: Recipe | undefined;

  constructor(private httpClient: HttpClient, private router: Router, activatedRoute: ActivatedRoute,private service: RecipesService) {

    let id = activatedRoute.snapshot.params['id'];
    service.getRecipe(id).subscribe(
      recipe => this.recipe = recipe,
      error => console.error(error)
  );
   }

	ngOnInit() {
		this.refresh();
	}

  private refresh() {
		this.httpClient.get(BASE_URL).subscribe(
			response => this.recipe = response as any,
			error => this.handleError(error)
		);
	}

  private handleError(error: any) {
		console.error(error);
	}

}
