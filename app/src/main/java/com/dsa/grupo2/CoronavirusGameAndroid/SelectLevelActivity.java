package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.LevelAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Game;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Level;
import com.dsa.grupo2.CoronavirusGameAndroid.services.GameService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ShopService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.dsa.grupo2.coronagame.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectLevelActivity extends AppCompatActivity implements LevelAdapter.OnItemClicked {

    GameService gameService;
    ShopService shopService;

    ArrayList<Level> levels;

    RecyclerView rv;

    int unlockedLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        rv = (RecyclerView)findViewById(R.id.levelView);
        rv.setHasFixedSize(true);


        gameService = ApiConn.getInstace().getGameService();
        shopService = ApiConn.getInstace().getShopService();


        Call<List<Level>> levelCall = gameService.getAllLevels();

        levelCall.enqueue(new Callback<List<Level>>() {
            @Override
            public void onResponse(Call<List<Level>> call, Response<List<Level>> response) {

                if (response.code() == 201) {
                    levels = (ArrayList<Level>) response.body();
                    showLevels();
                }
            }

            @Override
            public void onFailure(Call<List<Level>> call, Throwable t) {

            }
        });

        int c = 1;


    }

    public void showLevels() {

        Call<Game> gameCall = shopService.getGame(ApiConn.getInstace().getUserId());
        gameCall.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                unlockedLevels = response.body().getCompletedLevels();
                GridLayoutManager llm = new GridLayoutManager(getApplicationContext(), 3);
                rv.setLayoutManager(llm);
                LevelAdapter levelAdapter = new LevelAdapter(levels, unlockedLevels);
                rv.setAdapter(levelAdapter);
                levelAdapter.setOnClick(SelectLevelActivity.this);
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), UnityPlayerActivity.class);
        String test = levels.get(position).getMap();
        intent.putExtra("arguments",levels.get(position).getMap());
        startActivity(intent);
    }
}