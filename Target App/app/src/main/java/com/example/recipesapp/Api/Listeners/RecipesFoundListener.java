package com.example.recipesapp.Api.Listeners;

import com.example.recipesapp.Api.Models.Models.ListOfRecipes.Recipes;

import java.util.List;


public interface RecipesFoundListener {
    void didFetch(Recipes recipes, String message);
    void didError(String error);
}
