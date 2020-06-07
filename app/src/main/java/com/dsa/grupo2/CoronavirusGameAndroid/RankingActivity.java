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
    }
}