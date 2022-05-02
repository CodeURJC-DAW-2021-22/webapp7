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
    storedDiets: Diet[];

    isHealthy(){
      return this.activeMenu.healthy;
    }
}
