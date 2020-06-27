package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.dsa.grupo2.coronagame.UnityPlayerActivity;

public class TemporalUnityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, UnityPlayerActivity.class));
    }
}