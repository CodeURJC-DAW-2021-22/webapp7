package com.example.demo.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    private List<Recipe> weeklyPlan;


    public Menu() {
        weeklyPlan = new ArrayList<Recipe>();
    }

    public Menu(String name, List weeklyPlan){
        this.weeklyPlan=weeklyPlan;
        this.name=name;
    }


    public int[] getMenuScore(){
        int[] score = new int[10];

        //vegetables
        score[0]=8;
        score[1]=0;

        //protein
        score[2]=5;
        score[3]=0;

        //hydrates
        score[4]=3;
        score[5]=0;

        //carbohydrates
        score[6]=2;
        score[7]=0;

        //high in fat
        score[8]=1;
        score[9]=0;


        for(Recipe r : this.weeklyPlan){
            if (r.getVegetables())
                score[1]=score[1]+1;
            if(r.getProtein())
                score[3]=score[3]+1;
            if(r.getHydrates())
                score[5]=score[5]+1;
            if(r.getCarbohydrates())
                score[7]=score[7]+1;
            if(r.getHighinfat())
                score[9]=score[9]+1;
        }
        return score;
    }

    public void addDinner(Recipe meal, int n){
        weeklyPlan.add(n*2,meal);
    }
    public void addLunch(Recipe meal, int n){
        weeklyPlan.add(n,meal);
    }
    public List<Recipe> getLunchs(){
        List l = new ArrayList();
        int n = weeklyPlan.size()-1;
        int i=0;
        while(i<n){
            l.add(this.weeklyPlan.get(i));
            i=i+2;
        }
        return l;
    }
    public List<Recipe> getDinners(){
        List l = new ArrayList();
        int n = weeklyPlan.size()-1;
        int i=1;
        while(i<=n){
            l.add(this.weeklyPlan.get(i));
            i=i+2;
        }
        return l;
    }
    public List<Recipe> getWeeklyPlan() {
        return weeklyPlan;
    }
    public void setWeeklyPlan(List<Recipe> weeklyPlan) {
        this.weeklyPlan = weeklyPlan;
    }
    public void setId(Long id) {this.id = id;}
    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Long getId() {return id;}

}
