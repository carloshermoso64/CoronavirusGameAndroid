package com.dsa.grupo2.CoronavirusGameAndroid.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.dsa.grupo2.CoronavirusGameAndroid.models.*;

import java.util.List;

public interface BestLevelService {

    @GET("stats/userranking")//Devuelve los usuarios ordenados por nivel
    Call<List<User>> userRanking();

    @GET("stats/levelscores/{levelnumber}")//Devuelve los BestLevel de un mismo nivel ordenados
    Call<List<BestLevelTO>> levelScores(@Path("levelnumber") int levelnumber);

    @GET("stats/userscores/{username}")//Devuelve los BestLevel un usuario
    Call<List<BestLevel>> userScores(@Path("username") String username);
}
