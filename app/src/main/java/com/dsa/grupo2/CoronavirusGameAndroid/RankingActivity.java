package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dsa.grupo2.CoronavirusGameAndroid.models.User;

import java.util.LinkedList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    final LoadingDialog loadingDialog = new LoadingDialog(RankingActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        loadingDialog.startLoadingDialog();
        //PETICION A LA BD
        List<User> userList = new LinkedList<>();
        User primero = new User("anas", 3);
        User segundo = new User("pepito", 1);
        userList.add(primero);
        userList.add(segundo);
        TableLayout lista = findViewById(R.id.RankingTable);
        TableRow row = new TableRow(getBaseContext());
        TextView textViewUser;
        TextView textViewExp;
        for (int i=0; i < userList.size(); i++){
            textViewUser = new TextView(getBaseContext());
            textViewUser.setGravity(Gravity.CENTER_VERTICAL);
            textViewUser.setPadding(15,15,15,15);
            textViewUser.setBackgroundResource(R.color.colorPrimary);
            textViewUser.setText(userList.get(i).getId());
            textViewUser.setTextColor(Color.WHITE);
            row.addView(textViewUser);
            textViewExp = new TextView(getBaseContext());
            textViewExp.setGravity(Gravity.CENTER_VERTICAL);
            textViewExp.setPadding(15,15,15,15);
            textViewExp.setBackgroundResource(R.color.colorPrimary);
            textViewExp.setText(userList.get(i).getId());
            textViewExp.setTextColor(Color.WHITE);
            row.addView(textViewExp);
        }
        lista.addView(row);
        loadingDialog.dismissDialog();
    }
}