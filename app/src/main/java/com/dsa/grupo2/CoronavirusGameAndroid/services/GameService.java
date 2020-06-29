package com.dsa.grupo2.CoronavirusGameAndroid.services;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Level;
import com.dsa.grupo2.CoronavirusGameAndroid.models.LevelCompleted;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GameService {

    @GET("game/levels")
    Call<List<Level>> getAllLevels();

    @POST("game/levelcompleted")
    Call<LevelCompleted> completedLevels(LevelCompleted lvl);

}
