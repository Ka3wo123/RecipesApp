package com.example.recipesapp.fridge;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewPopup extends AppCompatDialogFragment {
    private EditText name;
    private TextView expDate;
    private AddNewListener addNewListener;
    private DatePickerDialog datePickerDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_add_new, null);

        builder.setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    String productName = name.getText().toString();
                    String date = expDate.getText().toString();

                    if (!productName.isEmpty() && !date.isEmpty()) {
                        addNewListener.apply(productName, date);
                    } else {
                        Toast.makeText(getContext(), "Product name or exp. date not provided", Toast.LENGTH_SHORT).show();
                    }


                });


        name = view.findViewById(R.id.addProductShopping);
        expDate = view.findViewById(R.id.quantityEditText);

        expDate.setOnClickListener(v -> initDatePicker());


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            addNewListener = (AddNewListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement AddNewListener");
        }
    }

    interface AddNewListener {
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

        datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);

        datePickerDialog.show();
    }

    private String makeStringDate(int day, int month, int year) {
        if (day < 10 && month < 10) {
            return year + "-0" + month + "-0" + day;
        }

        if (day < 10) {
            return year + "-" + month + "-0" + day;
        }

        if (month < 10) {
            return year + "-0" + month + "-" + day;
        }
        return year + "-" + month + "-" + day;
    }
}
