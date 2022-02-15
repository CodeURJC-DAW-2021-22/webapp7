package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @Column(columnDefinition = "TEXT")
    String preparation;

    String name;
    Integer cookTime;
    String difficulty;
    Date uploadDate;
    String ingredients;
    String user;

    @ElementCollection
    List<Boolean> categories;


    public Recipe(String name, Integer cookTime, String difficulty, Date uploadDate, String user, String ingredients, List<Boolean> categories, String preparation) {
        this.name = name;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.uploadDate = uploadDate;
        this.user = user;

        Scanner sc = new Scanner(ingredients);
        sc.useDelimiter(",");
        this.ingredients = ingredients;
        this.categories = categories;
        this.preparation = preparation;
    }

    public Recipe() {

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<Boolean> getCategories() {
        return categories;
    }

    public void setCategories(List<Boolean> categories) {
        this.categories = categories;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
