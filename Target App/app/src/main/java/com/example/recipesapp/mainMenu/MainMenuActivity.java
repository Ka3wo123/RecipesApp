package com.example.recipesapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.recipesapp.Api.Listeners.FridgeProductListener;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Api.RequestManagerDatabase;
import com.example.recipesapp.R;
import com.example.recipesapp.fridge.FridgeActivity;
import com.example.recipesapp.recipes.FoundRecipesActivity;
import com.example.recipesapp.recipes.SearchRecipeActivity;
import com.example.recipesapp.shopping.ShoppingActivity;
import com.example.recipesapp.wine.WineActivity;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private Button toFridge, toAvailable, toRecipes, toWine, toList, logout;
    private ImageSlider imageSlider;
    private TextView usernameLabel;
    private String username, query;
    private RequestManagerDatabase requestManager;

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
        usernameLabel = findViewById(R.id.usernameMenu);
        logout = findViewById(R.id.userDataButton);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        usernameLabel.setText(username + "!");

        requestManager = new RequestManagerDatabase(this);
        requestManager.getFridgeProducts(fridgeProductListener, username);

        bindActivity(toFridge, FridgeActivity.class);
        bindActivity(toAvailable, FoundRecipesActivity.class);
        bindActivity(toRecipes, SearchRecipeActivity.class);
        bindActivity(toWine, WineActivity.class);
        bindActivity(toList, ShoppingActivity.class);

        creatingSlideGalery(imageSlider);

        logout.setOnClickListener(v -> {
            LogoutPopup popup = new LogoutPopup();
            popup.show(getSupportFragmentManager(), "Logout popup");
        });

    }

    void bindActivity(Button button, Class<?> activity) {
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, activity);
            if (button == toAvailable) {
                intent.putExtra("username", username);
                intent.putExtra("fromFridge", true);
                intent.putExtra("query", query);
                Log.v("ingredients", query);
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

    private final FridgeProductListener fridgeProductListener = new FridgeProductListener() {
        @Override
        public void didFetch(List<FridgeProduct> fridgeProductListener, String message) {
            StringBuilder builder = new StringBuilder();
            for (FridgeProduct ingredient : fridgeProductListener) {
                builder.append(ingredient.productName).append(",");
            }
            builder.deleteCharAt(builder.length() - 1);
            query = String.valueOf(builder);

        }

        @Override
        public void didError(String error) {
            Log.v("errorIngredients", error);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        requestManager.getFridgeProducts(fridgeProductListener, username);
    }
}