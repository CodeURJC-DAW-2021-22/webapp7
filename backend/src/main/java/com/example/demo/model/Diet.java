package com.example.demo.model;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;

public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    ArrayList<Menu> dieta;




    public ArrayList<Menu> getDieta() {
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

}
