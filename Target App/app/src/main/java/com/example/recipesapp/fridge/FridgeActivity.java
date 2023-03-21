package com.example.recipesapp.fridge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.recipesapp.Libraries.Product;
import com.example.recipesapp.Libraries.ProductAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity implements AddNewPopup.FridgeDialogListener {
    @Override
    public void apply(String name, String date) {
        // TODO trzeba dodawać jeszcze rzecz jasna do bazy danych
        products.add(new Product(name, date));
        productAdapter.notifyDataSetChanged();
    }

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Button addNewBtn, sortBtn, saveBtn;
    private ImageButton backBtn;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        backBtn = findViewById(R.id.back);
        addNewBtn = findViewById(R.id.addNew);
        sortBtn = findViewById(R.id.sort);
        saveBtn = findViewById(R.id.save);

        backBtn.setOnClickListener(v -> finish());

        addNewBtn.setOnClickListener(v -> {
            AddNewPopup popUp = new AddNewPopup();
            popUp.show(getSupportFragmentManager(), "Add new popup");
        });

        sortBtn.setOnClickListener(v -> {
            SortPopup popUp = new SortPopup();
            popUp.show(getSupportFragmentManager(), "Sort popup");
        });


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