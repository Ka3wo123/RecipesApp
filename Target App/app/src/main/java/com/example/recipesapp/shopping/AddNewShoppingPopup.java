package com.example.recipesapp.shopping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.recipesapp.R;


public class AddNewShoppingPopup extends AppCompatDialogFragment {
    private EditText name;
    private EditText quantityEditText;
    private AddNewListener addNewListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_add_new_shopping, null);

        builder.setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .setPositiveButton("OK", (dialog, which) -> {
                    String productName = name.getText().toString();
                    Integer quantity = Integer.parseInt(quantityEditText.getText().toString());
                    if (!productName.isEmpty()) {
                        addNewListener.apply(productName, quantity);
                    } else {
                        Toast.makeText(getContext(), "Product name or quantity not provided", Toast.LENGTH_SHORT).show();
                    }
                });



        name = view.findViewById(R.id.addProductShopping);
        quantityEditText = view.findViewById(R.id.quantityEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            addNewListener = (AddNewShoppingPopup.AddNewListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement AddNewListener");
        }
    }

    interface AddNewListener {
        void apply(String name, Integer quantity);
    }


}

