package com.dsa.grupo2.CoronavirusGameAndroid.services;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Game;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShopService {
    @GET("game/{userId}")
    Call<Game> getGame(@Path("userId") String userId);

    @GET("tienda/{userId}/buymaxlife")
    Call<Void> getLife(@Path("userId") String userId);

    @GET("tienda/{userId}/buymask")
    Call<Void> getMask(@Path("userId") String userId);

    @GET("tienda/{userId}/buyneuron")
    Call<Void> getNeuron(@Path("userId") String userId);


}
