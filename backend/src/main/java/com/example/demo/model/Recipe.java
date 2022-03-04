package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;
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

    @Lob
    @JsonIgnore
    Blob recipeImage;

    private boolean hasPhoto;



    private boolean vegetables;
    private boolean protein;
    private boolean hydrates;
    private boolean carbohydrates;
    private boolean highinfat;


    public Recipe(String name, Integer cookTime, String difficulty, Date uploadDate, String ingredients, String creator, boolean vegetables, boolean protein, boolean hydrates, boolean carbohydrates, boolean highinfat, String preparation) {
        this.name = name;
        this.cookTime = cookTime;
        this.difficulty = difficulty;
        this.uploadDate = uploadDate;
        this.ingredients = ingredients;
        this.creator = creator;
        this.vegetables = vegetables;
        this.protein = protein;
        this.hydrates = hydrates;
        this.carbohydrates = carbohydrates;
        this.highinfat = highinfat;
        this.preparation = preparation;
    }

    public Recipe() {

    }


    public Blob getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(Blob recipeImage) {
        this.recipeImage = recipeImage;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
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
