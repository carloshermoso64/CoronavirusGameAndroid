package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    public UserService service;
    public TextInputLayout textUser, textPassword, textEmail;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textUser = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textEmail = findViewById(R.id.textEmail);

        service = ApiConn.getInstace().getUserService();
        context = getApplicationContext();
    }

    public void register(View v) {
        User u = new User();
        u.setName(textUser.getEditText().getText().toString());
        u.setPassword(textPassword.getEditText().getText().toString());
        u.setEmail(textEmail.getEditText().getText().toString());
        Call<User> registerUser = service.register(u);
        registerUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 404) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                } else if (response.code() == 201) {
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void openLogInActivity(View v) {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}
