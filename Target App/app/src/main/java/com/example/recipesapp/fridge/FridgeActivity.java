package com.example.recipesapp.fridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recipesapp.Libraries.Product;
import com.example.recipesapp.Libraries.ProductAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.Date;

public class FridgeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);

        products.add(new Product("Mleko", "2023-09-1"));
        products.add(new Product("Kawa","2025-4-6"));
        products.add(new Product("Chleb","1999-5-15"));
        products.add(new Product("Wiśnie","1999-5-15"));
        products.add(new Product("Przyprawy","1999-5-15"));
        products.add(new Product("Woda","1999-5-15"));
        products.add(new Product("Jack Daniel's","1999-5-15"));


        productAdapter.notifyDataSetChanged();

    }


}