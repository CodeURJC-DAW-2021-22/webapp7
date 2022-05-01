import { Diet } from "../Diet/diet";
import { Menu } from "../Menu/menu";
import { Recipes } from "../Recipes/recipes";

export class Users {
    id: Number;
    name: String;
    password: String;
    mail: String;
    admin: boolean;

    activeMenu: Menu;
    storedRecipes: Recipes[];
    soredDiets: Diet[];

    isHealthy(){
      var score = this.activeMenu.score;
      if (score<5){
        return false;
      }
      else{
        return true;
      }
    }
}
