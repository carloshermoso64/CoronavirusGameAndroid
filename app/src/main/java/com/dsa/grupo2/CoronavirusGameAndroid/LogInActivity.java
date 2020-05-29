package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Credentials;
import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {


    public UserService service;
    public TextInputLayout textUser, textPassword;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textUser = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);

        service = ApiConn.getInstace().getUserService();
        context = getApplicationContext();
    }

    public void logIn(View v) {
        Credentials cred = new Credentials();
        cred.setName(textUser.getEditText().getText().toString());
        cred.setPassword(textPassword.getEditText().getText().toString());

        Call<String> login = service.logIn(cred);
        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
