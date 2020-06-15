package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Token;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    final LoadingDialog loadingDialog = new LoadingDialog(MenuActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button playbtn = (Button) findViewById(R.id.PlayButton);
        final Button rankingbtn = (Button) findViewById(R.id.RankingButton);
        final Button storebtn = (Button) findViewById(R.id.StoreButton);
        final Button editProfileBtn = findViewById(R.id.buttonEditProfile);
        ImageView profileImage = findViewById(R.id.menuProfileImage);
        TextView usernameText = findViewById(R.id.menuUsernameText);
        usernameText.setText(ApiConn.getInstace().getUsername());
        Picasso.get().load("http://localhost:8080/dsaApp/user/image/"+ApiConn.getInstace().getUserId()).fit().transform(new CircleTransform())
                .placeholder(R.drawable.defaultprofile)
                .error(R.drawable.defaultprofile).into(profileImage);


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


    public void openForumActivity(View v) {
        Intent intent = new Intent(this, ForumMainMenu.class);
        startActivity(intent);
    }
    public void openShopActivity(){
        Intent intent = new Intent(this,ShopActivity.class);
        loadingDialog.dismissDialog();
        startActivity(intent);
    }
    public void openEditProfile(){
        loadingDialog.dismissDialog();
        startActivity(new Intent(this,EditProfileActivity.class));
    }

    public void openLevelSelect(View v) {
        startActivity(new Intent(this, SelectLevelActivity.class));
    }


    public void logOut(View v) {
        Token t = new Token(ApiConn.getInstace().getUserId(),ApiConn.getInstace().getUserToken());
        Call<Void> logOutCall = ApiConn.getInstace().getUserService().logOut(t);
        logOutCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                getApplicationContext().getSharedPreferences("coronavirusgame", Context.MODE_PRIVATE).edit().clear().commit();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }


}