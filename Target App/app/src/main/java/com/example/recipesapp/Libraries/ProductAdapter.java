package com.example.recipesapp.Libraries;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        LocalDate diffYears = holderDate.minusYears(currentDate.getYear());
        LocalDate diffMonths = holderDate.minusMonths(currentDate.getMonthValue());
        LocalDate diffDays = holderDate.minusDays(currentDate.getDayOfMonth());

        //TODO month >
//        int year = diffYears.getYear();
//        int month = diffMonths.getMonthValue();
//        int dayOfMonth = diffDays.getDayOfMonth();

        int year = holderDate.getYear() - currentDate.getYear();
        int month = holderDate.getMonthValue() - currentDate.getMonthValue();
        int dayOfMonth = holderDate.getDayOfMonth() - currentDate.getDayOfMonth();


        Log.v("diff", "X " + year + " " + month + " " + dayOfMonth);
        if (year < 0 || (year == 0 && month < 0) || (year == 0 && month == 0 && dayOfMonth < 0)) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
        }

//        if(year == 0 && month < 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
//        }
//
//        if(year == 0 && month == 0 && dayOfMonth < 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
//        }

        if (year == 0 && month == 0 && dayOfMonth == 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#F8C4B4"));
        }

        if (dayOfMonth > 0 && dayOfMonth < 7) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#E5EBB2"));
        }


//        String[] split = holder.expDate.getText().toString().split("\\.");
//        String[] currentDateSplit = currentDate.toString().split("-");
//
//        Map<String, Integer> dateDiff = new HashMap<>();
//        dateDiff.put("year", Integer.parseInt(split[0]) - Integer.parseInt(currentDateSplit[0]));
//        dateDiff.put("month", Integer.parseInt(split[1]) - Integer.parseInt(currentDateSplit[1]));
//        dateDiff.put("day", Integer.parseInt(split[2]) - Integer.parseInt(currentDateSplit[2]));
//
//        if (dateDiff.get("year") < 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
//        }
//        if (dateDiff.get("year") == 0 && dateDiff.get("month") < 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
//        }
//        if (dateDiff.get("year") == 0 && dateDiff.get("month") == 0 && dateDiff.get("day") < 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FF6161"));
//        }
//
//        if (dateDiff.get("year") == 0 && dateDiff.get("month") == 0 && dateDiff.get("day") == 0) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#F8C4B4"));
//        }
//
//        if (dateDiff.get("year") == 0 && dateDiff.get("month") > 0 && (dateDiff.get("day") > 0 && dateDiff.get("day") < 7)) {
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#E5EBB2"));
//        }


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
