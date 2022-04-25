import { RouterModule } from '@angular/router';

import { RecipespecificComponent } from './Templates/Recipe/recipespecific/recipespecific.component';

const appRoutes = [
    { path: 'recipe/:id', component: RecipespecificComponent }
]

export const routing = RouterModule.forRoot(appRoutes);
