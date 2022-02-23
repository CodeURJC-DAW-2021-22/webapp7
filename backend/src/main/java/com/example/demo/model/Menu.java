package com.example.demo.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_Recipe", referencedColumnName = "id")
    private List<Recipe> weeklyPlan;


    public Menu() {
        weeklyPlan = new ArrayList<Recipe>();
    }

    public Menu(String nombre, List weeklyPlan){
        this.weeklyPlan=weeklyPlan;
        this.nombre=nombre;
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {return id;}

}
