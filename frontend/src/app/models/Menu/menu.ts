import { Recipes } from "../Recipes/recipes";

export class Menu {
    id: number;
    name: string;
    weeklyPlan: Recipes[];
    healthy: boolean;
    score: number;


    /*getScore(){
      var total = this.weeklyPlan.length;
      for(let i = 0; i < total;i++){
        if(this.weeklyPlan[i].vegetbales){
          this.score[0]=this.score[0]+1;
        }
        if(this.weeklyPlan[i].protein)
                this.score[1]=this.score[1]+1;
        if(this.weeklyPlan[i].hydrates)
                this.score[2]=this.score[2]+1;
        if(this.weeklyPlan[i].carbohydrates)
                this.score[3]=this.score[3]+1;
        if(this.weeklyPlan[i].highinfat)
                this.score[4]=this.score[4]+1;
      }
      var pointsVegetables = this.score[0]-8;
        var pointsProtein = this.score[1]-5;
        var pointsHydrates= 3-this.score[2];
        var pointsCHydrates= this.score[3]-2;
        var pointsFat = 1-this.score[4];
        var totalScore = 12-(Math.abs(pointsVegetables+pointsProtein+pointsHydrates+pointsCHydrates+pointsFat));
        return totalScore;



    }*/
}
