package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Context context = getApplicationContext();
        sharedPref = context.getSharedPreferences("coronavirusgame", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token","notoken");
        String name = sharedPref.getString("name", "noname");
        String id = sharedPref.getString("id", "noid");
        String email = sharedPref.getString("email", "noemail");
        String password = sharedPref.getString("password", "nopassword");
        ApiConn.getInstace();

        if (!token.equals("notoken")) {
            ApiConn.getInstace().setUserToken(token);
            ApiConn.getInstace().setUsername(name);
            ApiConn.getInstace().setUserId(id);
            ApiConn.getInstace().setEmail(email);
            ApiConn.getInstace().setPassword(password);
            mRunnable = () -> startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        }

        else {
            int c = 1;
            mRunnable = () -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        }
        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, 2000);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRunnable != null && mHandler != null)
            mHandler.removeCallbacks(mRunnable);
    }
}