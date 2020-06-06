package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    final LoadingDialog loadingDialog = new LoadingDialog(LogInActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadingDialog.startLoadingDialog();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loadingDialog.dismissDialog();

        textUser = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);

        service = ApiConn.getInstace().getUserService();
        context = getApplicationContext();

        sharedPref = context.getSharedPreferences("coronavirusgame", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void logIn(View v) {
        loadingDialog.startLoadingDialog();
        Credentials cred = new Credentials();
        cred.setName(textUser.getEditText().getText().toString());
        cred.setPassword(textPassword.getEditText().getText().toString());

        Call<String> login = service.logIn(cred);

        login.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 201) {
                    String token = response.body();
                    ApiConn.getInstace().setUserToken(token);
                    ApiConn.getInstace().setUsername(textUser.getEditText().getText().toString());
                    editor.putString("token", token);
                    editor.putString("name", textUser.getEditText().getText().toString());
                    editor.commit();
                    startActivity(new Intent(context, MenuActivity.class));
                }
                else{
                    Toast.makeText(context,"Login Failed",Toast.LENGTH_LONG).show();
                }
                loadingDialog.dismissDialog();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
                loadingDialog.dismissDialog();
            }
        });
    }

}
