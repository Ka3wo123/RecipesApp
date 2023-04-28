package com.example.recipesapp.mainMenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.recipesapp.R;

public class LogoutPopup extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_logout, null);

        builder.setView(view)
                .setNegativeButton("No", (dialog, which) -> {
                })
                .setPositiveButton("Yes", (dialog, which) -> {
                    ((Activity) getContext()).finish();
                });

        return builder.create();
    }
}
