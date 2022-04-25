import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls:['../assets/css/styles.css',
  '../assets/css/bootstrap.min.css',
  '../assets/css/donwload.css',
  '../assets/css/font-awesome.min.css',
  '../assets/css/tables.css']
})
export class AppComponent {
  title = "Food & U"
  logged = true;
  admin = true;


  isLogged(logged:boolean){
    this.logged=logged;
  }

  isAdmin(admin:boolean){
    this.admin=admin
  }
}
