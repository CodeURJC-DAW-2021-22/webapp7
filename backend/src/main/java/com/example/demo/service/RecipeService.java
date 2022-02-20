package com.example.demo.service;


import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private RecipeService(RecipeRepository recipeRepository){

        this.recipeRepository = recipeRepository;
    }

    public Recipe recipeRegister(String nameRecipe, int cookTime, String difficulty, String ingredients, String preparation, String creator, Date date){
        if(nameRecipe == null || cookTime == -1 || difficulty == null || ingredients == null || preparation == null){
            return null;
        }else{
            Recipe recipeModel = new Recipe();
            recipeModel.setName(nameRecipe);
            recipeModel.setCookTime(cookTime);
            recipeModel.setDifficulty(difficulty);
            recipeModel.setIngredients(ingredients);
            recipeModel.setPreparation(preparation);
            recipeModel.setCreator(creator);
            recipeModel.setUploadDate(date);
            return recipeRepository.save(recipeModel);
        }
    }
}
