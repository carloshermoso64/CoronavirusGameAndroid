package com.dsa.grupo2.CoronavirusGameAndroid.services;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatService {

    @GET("chat")
    Call<List<Message>> getAllMessages();

    @POST("chat")
    Call<Void> sendMessage(@Body Message msg);
}
