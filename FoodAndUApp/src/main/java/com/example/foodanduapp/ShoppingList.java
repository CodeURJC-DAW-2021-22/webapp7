package com.example.foodanduapp;


import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ShoppingList {
    Map<String,Integer> numberofIntredients;
    ArrayList<String> list;


    public ShoppingList(Set<Recipe> weeklyFoods) {
        for(Recipe r : weeklyFoods){
            for(String s : r.getIngredients()){
                Integer n = numberofIntredients.get(s);
                if (n==null)
                    numberofIntredients.put(s,1);
                else
                    numberofIntredients.put(s,n+1);
            }
        }

        for (Map.Entry<String,Integer> e : numberofIntredients.entrySet()){
            String s = e.getKey() + " -> " + String.valueOf(e.getValue());
            list.add(s);
        }
    }
}
