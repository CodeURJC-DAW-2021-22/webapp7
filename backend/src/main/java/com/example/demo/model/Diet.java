package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String nombre;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Menu> dieta;


    public List<Menu> getDieta() {
        return dieta;
    }

    public void setDieta(ArrayList<Menu> dieta) {
        this.dieta = dieta;
    }

    public Diet() {
        this.dieta=new ArrayList<Menu>();
    }
    public void addtoDiet(Menu newMenu){
        dieta.add(newMenu);
    }

    public void removeMenuFromDiet(Menu m){
        dieta.remove(m);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDieta(List<Menu> dieta) {
        this.dieta = dieta;
    }
}
