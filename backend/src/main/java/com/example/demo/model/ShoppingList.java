package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Entity
public class ShoppingList {
    Map<String, Integer> numberofIntredients;
    List<String> list;
    private Long id;


    public ShoppingList() {
    }


    public ShoppingList(List<Recipe> weeklyPlan){

    }
    /*public ShoppingList(Recipe[] weeklyPlan) {
        for (Recipe r : weeklyPlan) {
            for (String s : r.getIngredients()) {
                Integer n = numberofIntredients.get(s);
                if (n == null)
                    numberofIntredients.put(s, 1);
                else
                    numberofIntredients.put(s, n + 1);
            }
        }

        for (Map.Entry<String, Integer> e : numberofIntredients.entrySet()) {
            String s = e.getKey() + " -> " + String.valueOf(e.getValue());
            list.add(s);
        }
    }*/

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}