import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './Templates/Admin/admin/admin.component';
import { UserComponent } from './Templates/User/user/user.component';
import { AboutusComponent } from './Templates/AboutUs/aboutus/aboutus.component';
import { RecipeallComponent } from './Templates/Recipe/recipeall/recipeall.component';
import { RecipespecificComponent } from './Templates/Recipe/recipespecific/recipespecific.component';
import { RecipemakerComponent } from './Templates/Recipe/recipemaker/recipemaker.component';
import { RecipeupdaterComponent } from './Templates/Recipe/recipeupdater/recipeupdater.component';
import { RecipestoredComponent } from './Templates/Recipe/recipestored/recipestored.component';
import { MenuactiveComponent } from './Templates/Menu/menuactive/menuactive.component';
import { MenuallComponent } from './Templates/Menu/menuall/menuall.component';
import { MenuloaderComponent } from './Templates/Menu/menuloader/menuloader.component';
import { MenumakerComponent } from './Templates/Menu/menumaker/menumaker.component';
import { DietmakerComponent } from './Templates/Diet/dietmaker/dietmaker/dietmaker.component';
import { DietstoredComponent } from './Templates/Diet/dietstored/dietstored/dietstored.component';
import { HomeComponent } from './Templates/Home/home/home.component';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Templates/Header/header/header.component';
import { FooterComponent } from './Templates/Footer/footer/footer.component';

const routes: Routes = [
  {path:'admin',component: AdminComponent},
  {path:'user',component: UserComponent},
  {path:'aboutus',component: AboutusComponent},
  {path:'recipeall',component: RecipeallComponent},
  {path:'recipespecific',component: RecipespecificComponent},
  {path:'recipemaker',component: RecipemakerComponent},
  {path:'recipeupdater',component: RecipeupdaterComponent},
  {path:'recipestored',component: RecipestoredComponent},
  {path:'menuactive',component: MenuactiveComponent},
  {path:'menuall',component: MenuallComponent},
  {path:'menuloader',component: MenuloaderComponent},
  {path:'menumaker',component: MenumakerComponent},
  {path:'dietmaker',component: DietmakerComponent},
  {path:'dietstored',component: DietstoredComponent},
  {path:'home',component: HomeComponent},
  {path:'header', component: HeaderComponent},
  {path:'footer', component: FooterComponent},
  {path:'index',component: AppComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
