package com.example.recipesapp.fridge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.recipesapp.Libraries.Product;
import com.example.recipesapp.Libraries.ProductAdapter;
import com.example.recipesapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class FridgeActivity extends AppCompatActivity implements AddNewPopup.AddNewListener {


    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Button addNewBtn, sortBtn;
    private ImageButton backBtn;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        backBtn = findViewById(R.id.back);
        addNewBtn = findViewById(R.id.addNew);
        sortBtn = findViewById(R.id.sort);


        backBtn.setOnClickListener(v -> finish());

        addNewBtn.setOnClickListener(v -> {
            AddNewPopup popUp = new AddNewPopup();
            popUp.show(getSupportFragmentManager(), "Add new popup");
        });

        sortBtn.setOnClickListener(v -> sortProducts());


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productAdapter = new ProductAdapter(this, products);
        recyclerView.setAdapter(productAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


    }


    @Override
    public void apply(String name, String date) {
        // TODO trzeba dodawać jeszcze rzecz jasna do bazy danych
        products.add(new Product(name, date));
        productAdapter.notifyItemInserted(products.size() - 1);
        recyclerView.scrollToPosition(products.size() - 1);
    }

    private void sortProducts() {
        Collections.sort(products);
        productAdapter.notifyItemRangeChanged(0, productAdapter.getItemCount());

    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            productAdapter.deleteItem(position);

        }


    };
}