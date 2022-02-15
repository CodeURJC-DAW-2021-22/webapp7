package com.example.demo.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Menu {


    @OneToMany
    List<Recipe> weeklyPlan;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public Menu() {
        weeklyPlan = new ArrayList<Recipe>();
    }




    public void addDinner(Recipe meal, int n){
        weeklyPlan.add(n*2,meal);
    }
    public void addLunch(Recipe meal, int n){
        weeklyPlan.add(n,meal);
    }
    public List<Recipe> getWeeklyPlan() {
        return weeklyPlan;
    }
    public void setWeeklyPlan(List<Recipe> weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }
    public void setId(Long id) {this.id = id;}

    public Long getId() {return id;}

}
