package com.example.demo.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

public class ShoppingList {
    @ElementCollection
    Map<String, Integer> numberofIntredients;
    @ElementCollection
    List<String> list;
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public ShoppingList() {
    }


    public ShoppingList(List<Recipe> weeklyPlan){

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}