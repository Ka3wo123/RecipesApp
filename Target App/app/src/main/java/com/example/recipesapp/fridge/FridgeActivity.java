package com.example.recipesapp.fridge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recipesapp.Api.Listeners.FridgeProductListener;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeList;
import com.example.recipesapp.Api.Models.Models.FridgeProducts.FridgeProduct;
import com.example.recipesapp.Api.RequestManagerDatabase;
import com.example.recipesapp.Libraries.ProductAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FridgeActivity extends AppCompatActivity implements AddNewPopup.AddNewListener {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private Button addNewBtn, sortBtn;
    private ImageButton backBtn;
    private RequestManagerDatabase requestManager;
    private String username;
    private FridgeList fridgeList = new FridgeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        backBtn = findViewById(R.id.backShopping);
        addNewBtn = findViewById(R.id.addNew);
        sortBtn = findViewById(R.id.sortButton);


        progressBar = findViewById(R.id.progressBarRecipes);
        progressBar.setVisibility(View.VISIBLE);

        requestManager = new RequestManagerDatabase(this);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        requestManager.getFridgeProducts(fridgeProductListener, username);
        
        backBtn.setOnClickListener(v -> finish());

        addNewBtn.setOnClickListener(v -> {
            AddNewPopup popUp = new AddNewPopup();
            popUp.show(getSupportFragmentManager(), "Add new popup");
        });

        sortBtn.setOnClickListener(v -> sortProducts());


        recyclerView = findViewById(R.id.recyclerViewShopping);



        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private final FridgeProductListener fridgeProductListener = new FridgeProductListener() {
        @Override
        public void didFetch(List<FridgeProduct> fridgeProductListener, String message) {
            progressBar.setVisibility(View.INVISIBLE);

            recyclerView.setLayoutManager(new LinearLayoutManager(FridgeActivity.this));

            if(fridgeProductListener.isEmpty()) {
                Toast.makeText(FridgeActivity.this, "Your fridge is empty", Toast.LENGTH_SHORT).show();
            }

            fridgeList.productsList = new ArrayList<>();

            for(FridgeProduct product : fridgeProductListener) {
                FridgeProduct fridgeProduct = new FridgeProduct();
                fridgeProduct.id = product.id;
                fridgeProduct.productName = product.productName;
                fridgeProduct.expirationDate = product.expirationDate;

                fridgeList.productsList.add(fridgeProduct);
            }
            productAdapter = new ProductAdapter(FridgeActivity.this, fridgeList.productsList);
            recyclerView.setAdapter(productAdapter);
        }
        @Override
        public void didError(String error) {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(FridgeActivity.this, "Server is down or other problem occurred", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void apply(String name, String date) {
        requestManager.addToFridge(new FridgeProduct(username, name, date));
        fridgeList.productsList.add(new FridgeProduct(name, date));
        productAdapter.notifyItemInserted(fridgeList.productsList.size() - 1);
        recyclerView.scrollToPosition(fridgeList.productsList.size() - 1);
    }

    private void sortProducts() {
        Collections.sort(fridgeList.productsList);
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
            requestManager.deleteFromFridge(fridgeList.productsList.get(position).id);
            productAdapter.deleteItem(position);

        }
    };
}