package com.example.demo.service;


import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Recipe> findAll(){return repository.findAll();}

    public Page<Recipe> findAll13(PageRequest pageRequest){return repository.findAll(pageRequest.of(1,3, Sort.by("id").descending()));}

    public Page<Recipe> findAll03(PageRequest pageRequest){return repository.findAll(pageRequest.of(0,3, Sort.by("id").descending()));}

    public Page<Recipe> findAll012(PageRequest pageRequest){return repository.findAll(pageRequest.of(0,12, Sort.by("id").descending()));}

    public Page<Recipe> findPage(PageRequest pageRequest,int page){return repository.findAll(pageRequest.of(page,12, Sort.by("id").descending()));}

    public long count(){return repository.count();}

    public Page<Recipe> findAll(Pageable page){return repository.findAll(page);}

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
