package com.example.foodanduapp;

public class Menu {
    Recipe[] weeklyPlan;

    public Menu() {
        weeklyPlan = new Recipe[14];
    }
    public void addDinner(Recipe meal, int n){
        weeklyPlan[n*2] = meal;
    }
    public void addLunch(Recipe meal, int n){
        weeklyPlan[n] = meal;
    }

    public Recipe[] getWeeklyPlan() {
        return weeklyPlan;
    }

    public void setWeeklyPlan(Recipe[] weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }
}
