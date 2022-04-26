import { NgModule } from '@angular/core';
//import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
/*import { AdminComponent } from './Templates/Admin/admin/admin.component';
import { UserComponent } from './Templates/User/user/user.component';
import { AboutusComponent } from './Templates/AboutUs/aboutus/aboutus.component';
import { RecipeallComponent } from './Templates/Recipe/recipeall/recipeall.component';*/
import { RecipespecificComponent } from './Templates/Recipe/recipespecific/recipespecific.component';
/*import { RecipemakerComponent } from './Templates/Recipe/recipemaker/recipemaker.component';
import { RecipeupdaterComponent } from './Templates/Recipe/recipeupdater/recipeupdater.component';*/
import { RecipestoredComponent } from './Templates/Recipe/recipestored/recipestored.component';
/*import { MenuactiveComponent } from './Templates/Menu/menuactive/menuactive.component';
import { MenuallComponent } from './Templates/Menu/menuall/menuall.component';
import { MenuloaderComponent } from './Templates/Menu/menuloader/menuloader.component';
import { MenumakerComponent } from './Templates/Menu/menumaker/menumaker.component';
import { DietmakerComponent } from './Templates/Diet/dietmaker/dietmaker/dietmaker.component';
import { DietstoredComponent } from './Templates/Diet/dietstored/dietstored/dietstored.component';
import { HomeComponent } from './Templates/Home/home/home.component';*/


@NgModule({
  declarations: [
    AppComponent,
    RecipespecificComponent,
    RecipestoredComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
