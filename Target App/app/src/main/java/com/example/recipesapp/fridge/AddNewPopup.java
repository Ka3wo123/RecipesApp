package com.example.recipesapp.fridge;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.recipesapp.R;

import java.util.Calendar;

public class AddNewPopup extends AppCompatDialogFragment {
    private EditText name;
    private TextView expDate;
    private FridgeDialogListener fridgeDialogListener;
    private DatePickerDialog datePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_add_new, null);

        builder.setView(view).setNegativeButton("Cancel", (dialog, which) -> {
        })

                .setPositiveButton("OK", (dialog, which) -> {
                    String productName = name.getText().toString();
                    String expirationDate = expDate.getText().toString();
                    fridgeDialogListener.apply(productName, expirationDate);
        });



        name = view.findViewById(R.id.addProductFridge);
        expDate = view.findViewById(R.id.expirationDateFridge);

        expDate.setOnClickListener(v -> {
            initDatePicker();

        });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            fridgeDialogListener = (FridgeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement FridgeDialogListener");
        }
    }

    interface FridgeDialogListener {
        void apply(String name, String date);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month++;
            String date = makeStringDate(dayOfMonth, month, year);
            expDate.setText(date);
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_DARK, dateSetListener, year, month, day);

        datePickerDialog.show();
    }

    private String makeStringDate(int day, int month, int year) {
        return day + "." + month + "." + year;
    }
}
