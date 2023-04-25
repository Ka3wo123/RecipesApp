package com.example.recipesapp.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.recipesapp.Libraries.ProductShopping;
import com.example.recipesapp.Libraries.ShoppingAdapter;
import com.example.recipesapp.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingActivity extends AppCompatActivity implements AddNewShoppingPopup.AddNewListener {
    private ShoppingAdapter adapter;
    private RecyclerView recyclerView;
    private List<ProductShopping> productShoppings = new ArrayList<>();
    private ImageButton back;
    private Button addNew, addToFridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        back = findViewById(R.id.backShopping);
        addNew = findViewById(R.id.addNew);
        addToFridge = findViewById(R.id.addAllToFridge);

        recyclerView = findViewById(R.id.recyclerViewShopping);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShoppingAdapter(this, productShoppings);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        back.setOnClickListener(v -> finish());
        addNew.setOnClickListener(v -> {
            AddNewShoppingPopup popup = new AddNewShoppingPopup();
            popup.show(getSupportFragmentManager(), "Popup shopping");
        });

        addToFridge.setOnClickListener(v -> {

        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            adapter.deleteItem(position);

        }
    };


    @Override
    public void apply(String name, String quantity) {
        productShoppings.add(new ProductShopping(name, quantity));
        adapter.notifyItemInserted(productShoppings.size() - 1);
        recyclerView.scrollToPosition(productShoppings.size() - 1);
    }
}