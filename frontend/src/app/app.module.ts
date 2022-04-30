import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminComponent } from './components/Admin/admin/admin.component';
import { UserComponent } from './components/User/user/user.component';
import { AboutusComponent } from './components/AboutUs/aboutus/aboutus.component';
import { RecipeallComponent } from './components/Recipe/recipeall/recipeall.component';
import { RecipespecificComponent } from './components/Recipe/recipespecific/recipespecific.component';
import { RecipemakerComponent } from './components/Recipe/recipemaker/recipemaker.component';
import { RecipeupdaterComponent } from './components/Recipe/recipeupdater/recipeupdater.component';
import { RecipestoredComponent } from './components/Recipe/recipestored/recipestored.component';
import { MenuactiveComponent } from './components/Menu/menuactive/menuactive.component';
import { MenuallComponent } from './components/Menu/menuall/menuall.component';
import { MenuloaderComponent } from './components/Menu/menuloader/menuloader.component';
import { MenumakerComponent } from './components/Menu/menumaker/menumaker.component';
import { DietmakerComponent } from './components/Diet/dietmaker/dietmaker/dietmaker.component';
import { DietstoredComponent } from './components/Diet/dietstored/dietstored/dietstored.component';
import { HomeComponent } from './components/Home/home/home.component';
import { HeaderComponent } from './components/Header/header/header.component';
import { FooterComponent } from './components/Footer/footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/Login/login/login.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    UserComponent,
    AboutusComponent,
    RecipeallComponent,
    RecipespecificComponent,
    RecipemakerComponent,
    RecipeupdaterComponent,
    RecipestoredComponent,
    MenuactiveComponent,
    MenuallComponent,
    MenuloaderComponent,
    MenumakerComponent,
    DietmakerComponent,
    DietstoredComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
