package com.example.demo.model;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;


    String name;
    Integer cookTime;
    String difficulty;
    Date uploadDate;
    User user;
    ArrayList<String> ingredients;
    boolean[] categories;
    String preparation;

    public Recipe(String name, Integer cookTime, String difficulty, Date uploadDate, User user, String ingredients, boolean[] categories, String preparation) {
        this.name = name;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.uploadDate = uploadDate;
        this.user = user;

        Scanner sc = new Scanner(ingredients);
        sc.useDelimiter(",");
        this.ingredients = new ArrayList<String>();
        while (sc.hasNext()){
            this.ingredients.add(sc.next());
        }
        this.categories = categories;
        this.preparation = preparation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean[] getCategories() {
        return categories;
    }

    public void setCategories(boolean[] categories) {
        this.categories = categories;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }
}
