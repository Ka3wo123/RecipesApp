package com.example.recipesapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recipesapp.Api.Models.Models.AccountDetails.Account;
import com.example.recipesapp.Api.RequestManager;
import com.example.recipesapp.R;
import com.example.recipesapp.mainMenu.MainMenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RegistrationActivity extends AppCompatActivity {

    private Button returnButton, signUpBtn;
    private EditText name, username, password, conifrmPassword;
    private RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        returnButton = findViewById(R.id.returnToLogin);
        signUpBtn = findViewById(R.id.signUpButton);
        name = findViewById(R.id.yourName);
        username = findViewById(R.id.login);
        password = findViewById(R.id.password1);
        conifrmPassword = findViewById(R.id.password2);

        requestManager = new RequestManager(this);

        signUpBtn.setOnClickListener(v -> {
            createAccount();
            finish();
        });
        returnButton.setOnClickListener(v -> finish());
    }

    void createAccount() {
        Retrofit databaseRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.58:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CallRegistration accountCall = databaseRetrofit.create(CallRegistration.class);

        String nameAcc = name.getText().toString();
        String usernameAcc = username.getText().toString();
        String password1Acc = password.getText().toString();
        String password2Acc = conifrmPassword.getText().toString();

        Call<Boolean> call = accountCall.createAccount(new Account(nameAcc,
                usernameAcc,
                password1Acc));

        if (!password1Acc.equals(password2Acc)) {
            Toast.makeText(this, "Passwords are different", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent mainMenu = new Intent(RegistrationActivity.this, MainMenuActivity.class);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (Boolean.TRUE.equals(response.body())) {
                    mainMenu.putExtra("username", usernameAcc);
                    mainMenu.putExtra("fromRegistration", true);
                    startActivity(mainMenu);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("OnfailureREgistration", t.getMessage());
            }
        });
    }

    private interface CallRegistration {
        @POST("/account")
        Call<Boolean> createAccount(@Body Account account);
    }
}