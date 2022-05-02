import { LoginComponent } from './components/Login/login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './components/Admin/admin/admin.component';
import { ErrorComponent } from './components/error/error.component';
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
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/Header/header/header.component';
import { FooterComponent } from './components/Footer/footer/footer.component';
import { DietloaderComponent } from './components/Diet/dietloader/dietloader.component';

const routes: Routes = [
  {path:'admin',component: AdminComponent},
  {path:'error',component: ErrorComponent},
  {path:'login',component: LoginComponent},
  {path:'user',component: UserComponent},
  {path:'aboutus',component: AboutusComponent},
  {path:'recipeall',component: RecipeallComponent},
  {path:'recipespecific/:id',component: RecipespecificComponent},
  {path:'recipemaker',component: RecipemakerComponent},
  {path:'recipeupdater/:id',component: RecipeupdaterComponent},
  {path:'recipestored',component: RecipestoredComponent},
  {path:'menuactive',component: MenuactiveComponent},
  {path:'menuall',component: MenuallComponent},
  {path:'menuloader/:id',component: MenuloaderComponent},
  {path:'menumaker',component: MenumakerComponent},
  {path:'dietmaker',component: DietmakerComponent},
  {path:'dietloader/:id',component: DietloaderComponent},
  {path:'dietstored',component: DietstoredComponent},
  {path:'home',component: HomeComponent},
  {path:'header', component: HeaderComponent},
  {path:'footer', component: FooterComponent},
  {path:'index',component: AppComponent},
  {path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
