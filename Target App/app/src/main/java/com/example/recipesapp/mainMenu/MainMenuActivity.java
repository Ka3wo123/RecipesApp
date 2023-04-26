package com.example.recipesapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.recipesapp.Libraries.GlobalData;
import com.example.recipesapp.R;
import com.example.recipesapp.authentication.LoginActivity;
import com.example.recipesapp.authentication.RegistrationActivity;
import com.example.recipesapp.fridge.FridgeActivity;
import com.example.recipesapp.recipes.FoundRecipesActivity;
import com.example.recipesapp.recipes.SearchRecipeActivity;
import com.example.recipesapp.shopping.ShoppingActivity;
import com.example.recipesapp.wine.WineActivity;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    Button toFridge, toAvailable, toRecipes, toWine, toList;
    ImageSlider imageSlider;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        toFridge = findViewById(R.id.toFridge);
        toAvailable = findViewById(R.id.toAvaiable);
        toRecipes = findViewById(R.id.toRecipes);
        toWine = findViewById(R.id.toWine);
        toList = findViewById(R.id.toList);
        imageSlider = findViewById(R.id.imageSlider);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        bindActivity(toFridge, FridgeActivity.class);
        bindActivity(toAvailable, FoundRecipesActivity.class);
        bindActivity(toRecipes, SearchRecipeActivity.class);
        bindActivity(toWine, WineActivity.class);
        bindActivity(toList, ShoppingActivity.class);

        creatingSlideGalery(imageSlider);

    }

    void bindActivity(Button button, Class<?> activity) {
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, activity);
            if (button == toAvailable) {
                intent.putExtra("fromFridge", true);
            } else if (button == toFridge || button == toList) {
                intent.putExtra("username", username);
            }
            startActivity(intent);
        });
    }

    void creatingSlideGalery(ImageSlider imageSlider) {
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.is1, null));
        images.add(new SlideModel(R.drawable.is2, null));
        images.add(new SlideModel(R.drawable.is3, null));
        images.add(new SlideModel(R.drawable.is4, null));
        images.add(new SlideModel(R.drawable.is5, null));
        images.add(new SlideModel(R.drawable.is6, null));

        imageSlider.setImageList(images, ScaleTypes.CENTER_CROP);

    }
}