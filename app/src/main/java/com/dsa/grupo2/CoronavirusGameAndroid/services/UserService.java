package com.dsa.grupo2.CoronavirusGameAndroid.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.dsa.grupo2.CoronavirusGameAndroid.models.*;

public interface UserService {

    @POST("user/adduser")
    Call<User> register(@Body User user);

    @POST("user/login")
    Call<String> logIn(@Body Credentials cred);

    @GET("user/{name}")
    Call<User> getUser(@Path("name") String name);
}
