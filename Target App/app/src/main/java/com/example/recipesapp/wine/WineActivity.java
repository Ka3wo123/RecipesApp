package com.example.recipesapp.wine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.WineMatchListener;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.Api.models.Wine.WineMatches;
import com.example.recipesapp.R;

import java.util.List;

public class WineActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        dialog = new ProgressDialog(this);

        manager = new RequestManager(this);
        manager.getPairedWine(wineMatchListener, "steak");
        dialog.show();

    }

    private final WineMatchListener wineMatchListener = new WineMatchListener() {
        @Override
        public void didFetch(WineMatches wineMatches, String message) {
            Toast.makeText(WineActivity.this, wineMatches.pairingText, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        }

        @Override
        public void didError(String error) {
            Toast.makeText(WineActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };


}