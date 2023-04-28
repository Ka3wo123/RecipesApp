package com.example.recipesapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipesapp.R;
import com.example.recipesapp.mainMenu.MainMenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText username, password;
    private TextView registerView;
    private CheckBox checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginBtn = findViewById(R.id.button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerView = findViewById(R.id.signUpTv);
        checked = findViewById(R.id.rememberPassword);


        registerView.setOnClickListener(v -> goToRegisterActivity());

        loginBtn.setOnClickListener(v -> login());

        if(readCheckedFromSP()) {
            checked.setChecked(true);
            readLoginFromSP();
        }

    }

    public void login() {
        String login = username.getText().toString();
        String pass = password.getText().toString();

        saveCheckedToSP();

        Retrofit databaseRetrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.58:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        CallAccount accountCall = databaseRetrofit.create(CallAccount.class);
        Call<Boolean> call = accountCall.validateCredentials(login, pass);
        Intent mainMenu = new Intent(this, MainMenuActivity.class);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                boolean isValid = Boolean.TRUE.equals(response.body());
                if (isValid) {
                    mainMenu.putExtra("username", username.getText().toString());
                    startActivity(mainMenu);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("failure", t.getMessage());
            }
        });


    }

    private interface CallAccount {
        @GET("account/validate")
        Call<Boolean> validateCredentials(@Query("username") String username, @Query("password") String password);
    }

    public void goToRegisterActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


    public void readLoginFromSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
        String login = sharedPreferences.getString("login", "");
        EditText editText = findViewById(R.id.username);
        editText.setText(login);
    }

    boolean readCheckedFromSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
        String checked = sharedPreferences.getString("checked", "false");
        return checked.equals("true");
    }

    public void saveCheckedToSP() {
        CheckBox checked = findViewById(R.id.rememberPassword);
        EditText login = findViewById(R.id.username);
        if (checked.isChecked()) {
            SharedPreferences sharedPreferences = getSharedPreferences("RecipeApp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("checked", "true");
            editor.apply();
            editor.putString("login", String.valueOf(login.getText()));
            editor.apply();
        }

    }
}