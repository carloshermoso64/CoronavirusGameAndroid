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



        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                openRankingActivity();
            }
        });

        storebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                loadingDialog.startLoadingDialog();
                openShopActivity();;
            }
        });
    }
    public void openRankingActivity(){
        Intent intent = new Intent(this,RankingActivity.class);
        loadingDialog.dismissDialog();
        startActivity(intent);
    }


    public void openForumActivity(View v) {
        Intent intent = new Intent(this, ForumActivity.class);
        startActivity(intent);
    }
    public void openShopActivity(){
        Intent intent = new Intent(this,ShopActivity.class);
        loadingDialog.dismissDialog();
        startActivity(intent);
    }
}