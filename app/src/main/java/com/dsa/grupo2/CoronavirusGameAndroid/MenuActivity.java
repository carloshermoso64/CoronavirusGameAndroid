package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    final LoadingDialog loadingDialog = new LoadingDialog(MenuActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button playbtn = (Button) findViewById(R.id.PlayButton);
        final Button historybtn = (Button) findViewById(R.id.HistoryBurron);
        final Button rankingbtn = (Button) findViewById(R.id.RankingButton);
        final Button storebtn = (Button) findViewById(R.id.StoreButton);
        final Button editProfileBtn = findViewById(R.id.buttonEditProfile);

        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                openRankingActivity();
            }
        });

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                openEditProfile();
            }
        });

    }
    public void openRankingActivity(){
        Intent intent = new Intent(this,RankingActivity.class);
        loadingDialog.dismissDialog();
        startActivity(intent);
    }
    public void openEditProfile(){
        loadingDialog.dismissDialog();
        startActivity(new Intent(this,EditProfileActivity.class));
    }



}