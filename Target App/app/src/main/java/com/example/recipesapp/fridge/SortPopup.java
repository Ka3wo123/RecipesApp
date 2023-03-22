package com.example.recipesapp.fridge;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.recipesapp.Libraries.Product;
import com.example.recipesapp.R;

import java.util.ArrayList;

public class SortPopup extends AppCompatDialogFragment {
    private SortListener sortListener;
    private Button sortByName, sortByExpDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_sort, null);

        sortByName = view.findViewById(R.id.sortByName);
        sortByExpDate = view.findViewById(R.id.sortByExpDate);

        sortByName.setOnClickListener(v -> {
            sortListener.sortProductsBy();
        });

        sortByExpDate.setOnClickListener(v -> sortListener.sortProductsBy());

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            sortListener = (SortPopup.SortListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement SortListener");
        }
    }

    interface SortListener {
        void sortProductsBy();
    }
}
