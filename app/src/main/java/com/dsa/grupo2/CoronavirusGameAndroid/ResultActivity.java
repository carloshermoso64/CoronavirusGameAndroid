package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.LevelCompleted;
import com.dsa.grupo2.CoronavirusGameAndroid.services.GameService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    TextView resultText, resultScore, resultTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        resultScore = findViewById(R.id.pointScore);
        resultTime = findViewById(R.id.timeScore);

        Bundle extras = getIntent().getExtras();

        String levelCompleted = extras.getString("completed");
        int score = extras.getInt("score");
        int time = extras.getInt("time");
        String keepsMask = extras.getString("keepsMask");
        int levelNumber = extras.getInt("levelNumber");

        if (levelCompleted.equals("TRUE")) {
            resultText.setText("Congratulations! You made:");
            resultScore.setText(String.valueOf(score));
            resultTime.setText(String.valueOf(time));

            LevelCompleted lvl = new LevelCompleted();

            if (levelCompleted == "TRUE") {

                lvl = new LevelCompleted();
                lvl.setCompleted(true);
                lvl.setIdUser(ApiConn.getInstace().getUserId());
                if (keepsMask == "TRUE")
                    lvl.setKeepsMask(true);
                else
                    lvl.setKeepsMask(false);

                lvl.setLevelNumber(levelNumber);
                lvl.setScore(score);
                lvl.setTime(time);
                lvl.setStartTime(new Date());
            }

            else {
                lvl.setCompleted(false);
                lvl.setIdUser(ApiConn.getInstace().getUserId());
                lvl.setKeepsMask(false);
                lvl.setLevelNumber(levelNumber);
                lvl.setScore(0);
                lvl.setTime(0);
                lvl.setStartTime(new Date());
            }

            Call<LevelCompleted> finishLevelCall = ApiConn.getInstace().getGameService().completedLevels(lvl);
            finishLevelCall.enqueue(new Callback<LevelCompleted>() {
                @Override
                public void onResponse(Call<LevelCompleted> call, Response<LevelCompleted> response) {
                    Toast.makeText(getApplicationContext(), "Somos el pene", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<LevelCompleted> call, Throwable t) {

                }
            });
        }

        else {
            resultText.setText("Woops, you got infected!");
            resultScore.setVisibility(View.GONE);
            resultTime.setVisibility(View.GONE);
        }
    }

    public void goToMainMenu(View v) {
        startActivity(new Intent(this, MenuActivity.class));
    }
}