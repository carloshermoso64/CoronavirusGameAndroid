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
        ApiConn.getInstace();

        if (!token.equals("notoken")) {
            ApiConn.getInstace().setUserToken(token);
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
