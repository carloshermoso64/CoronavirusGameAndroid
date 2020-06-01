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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        List<User> userList = new LinkedList<>();
        User primero = new User("anas", 3);
        User segundo = new User("pepito", 1);
        userList.add(primero);
        userList.add(segundo);
        TableLayout lista = (TableLayout) findViewById(R.id.RankingTable);
        TableRow row = new TableRow(getBaseContext());
        TextView textView;
        for (int i=0; i < userList.size(); i++){
            textView = new TextView(getBaseContext());
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(15,15,15,15);
            textView.setBackgroundResource(R.color.colorPrimary);
            textView.setText(userList.get(i).getId());
            textView.setTextColor(Color.BLACK);
            row.addView(textView);
        }
        lista.addView(row);
    }
}