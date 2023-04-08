package com.example.recipesapp.Libraries;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipesapp.R;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private ArrayList<Product> products;
    private int lastPosition = -1;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        LocalDate currentDate = LocalDate.now();
        Product product = products.get(position);
        holder.name.setText(product.getName());
        holder.expDate.setText(product.getExpirationDate());

        LocalDate holderDate = LocalDate.parse(holder.expDate.getText());

        int year = holderDate.getYear() - currentDate.getYear();
        int month = holderDate.getMonthValue() - currentDate.getMonthValue();
        int dayOfMonth = holderDate.getDayOfMonth() - currentDate.getDayOfMonth();
        String expired = "#FF6161";
        String thisDay = "#F8C4B4";
        String nearExp = "#E5EBB2";
        String longUse = "#BCE29E";


        if (year < 0 || (year == 0 && month < 0) || (year == 0 && month == 0 && dayOfMonth < 0)) {
            holder.cardView.setCardBackgroundColor(Color.parseColor(expired));
        }

        else if (year == 0 && month == 0 && dayOfMonth == 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor(thisDay));
        }

        else if (year == 0 && month == 0 &&  dayOfMonth < 7) {
            holder.cardView.setCardBackgroundColor(Color.parseColor(nearExp));
        }

        else if(year == 0 && month == 1 && dayOfMonth <= -24 && dayOfMonth >= -28) {
            holder.cardView.setCardBackgroundColor(Color.parseColor(nearExp));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor(longUse));
        }

         if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            holder.cardView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }


    }

    public void deleteItem(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Product removed from fridge", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView expDate;
        private final CardView cardView;

        ProductHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            expDate = itemView.findViewById(R.id.expiarationDate);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
