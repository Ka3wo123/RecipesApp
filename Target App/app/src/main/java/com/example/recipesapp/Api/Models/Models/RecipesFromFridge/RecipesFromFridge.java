package com.example.recipesapp.Api.Models.Models.RecipesFromFridge;

import java.util.ArrayList;

public class RecipesFromFridge {
    public int id;
    public String title;
    public String image;
    public String imageType;
    public int usedIngredientCount;
    public int missedIngredientCount;
    public ArrayList<MissedIngredient> missedIngredients;
    public ArrayList<UsedIngredient> usedIngredients;
    public ArrayList<UnusedIngredient> unusedIngredients;
    public int likes;
}
