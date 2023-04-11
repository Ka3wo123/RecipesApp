package com.example.recipesapp.wine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.WineMatchListener;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Api.Models.Models.Wine.WineMatches;
import com.example.recipesapp.R;
import com.squareup.picasso.Picasso;

public class WineActivity extends AppCompatActivity {

    ProgressDialog dialog;
    ImageButton back2;
    RequestManager manager;
    EditText input;
    ImageView searchWine, winePhoto;
    TextView pairedWines, description, averagePrize, productMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        dialog = new ProgressDialog(this);
        input = findViewById(R.id.nameOfFoodForWine);
        searchWine = findViewById(R.id.searchWine);
        manager = new RequestManager(this);

        pairedWines = findViewById(R.id.tvPairedWines);
        description = findViewById(R.id.tvDescription);
        averagePrize = findViewById(R.id.tvAveragePrize);
        productMatch = findViewById(R.id.tvProductMatch);
        winePhoto = findViewById(R.id.winePhoto);
        back2 = findViewById(R.id.back2);

        back2.setOnClickListener(v -> {
            finish();
        });

        searchWine.setOnClickListener(v -> {
            if(!input.getText().toString().equals("")) {
                manager.getPairedWine(wineMatchListener, input.getText().toString());
                dialog.show();
            }
        });

    }

    private final WineMatchListener wineMatchListener = new WineMatchListener() {
        @Override
        public void didFetch(WineMatches wineMatches, String message) {
            if(wineMatches.productMatches == null) {
                Toast.makeText(WineActivity.this, "No wines found", Toast.LENGTH_SHORT).show();
            } else {
                setContent(wineMatches);
            }
            dialog.dismiss();
        }

        @Override
        public void didError(String error) {
            Toast.makeText(WineActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };

    void setContent(WineMatches wineMatches){
        String pairedWinesResult = wineMatches.pairedWines.get(0) +", "+ wineMatches.pairedWines.get(1) +", " + wineMatches.pairedWines.get(2);
        pairedWines.setText(pairedWinesResult);
        description.setText(wineMatches.pairingText);
        averagePrize.setText(wineMatches.productMatches.get(0).price);
        productMatch.setText(wineMatches.productMatches.get(0).title);
        Picasso.get().load(wineMatches.productMatches.get(0).imageUrl).into(winePhoto);

        pairedWines.setVisibility(View.VISIBLE);
        description.setVisibility(View.VISIBLE);
        averagePrize.setVisibility(View.VISIBLE);
        productMatch.setVisibility(View.VISIBLE);
    }

}