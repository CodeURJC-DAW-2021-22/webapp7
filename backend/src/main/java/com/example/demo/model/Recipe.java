package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String preparation;

    private String name;
    private Integer cookTime;
    private String difficulty;
    private Date uploadDate;
    private String ingredients;
    private String creator;



    private boolean vegetables;
    private boolean protein;
    private boolean hydrates;
    private boolean carbohydrates;
    private boolean highinfat;


    public Recipe(String name, Integer cookTime, String difficulty, Date uploadDate, String creator, String ingredients, boolean vegetables, boolean protein, boolean hydrates, boolean carbohydrates, boolean highinfat, String preparation) {
        this.name = name;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.uploadDate = uploadDate;
        this.creator = creator;

        this.vegetables = vegetables;
        this.protein = protein;
        this.hydrates = hydrates;
        this.carbohydrates = carbohydrates;
        this.highinfat = highinfat;

        Scanner sc = new Scanner(ingredients);
        sc.useDelimiter(",");
        this.ingredients = ingredients;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String user) {
        this.creator = user;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public boolean getVegetables() {
        return vegetables;
    }

    public void setVegetables(boolean vegetables) {
        this.vegetables = vegetables;
    }

    public boolean getProtein() {
        return protein;
    }

    public void setProtein(boolean protein) {
        this.protein = protein;
    }

    public boolean getHydrates() {
        return hydrates;
    }

    public void setHydrates(boolean hydrates) {
        this.hydrates = hydrates;
    }

    public boolean getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(boolean carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public boolean getHighinfat() {
        return highinfat;
    }

    public void setHighinfat(boolean highinfat) {
        this.highinfat = highinfat;
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
