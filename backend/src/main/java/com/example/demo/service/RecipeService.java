package com.example.demo.service;


import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository repository;

    public RecipeService(RecipeRepository recipeRepository){

        this.repository = recipeRepository;
    }

    public Optional<Recipe> findById(long id) {
        return repository.findRecipeById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);}

    public void save(Recipe recipe){
        repository.save(recipe);
    }

    public void delete(long id) {
        repository.deleteById(id);
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
            return repository.save(recipeModel);
        }
    }
}
