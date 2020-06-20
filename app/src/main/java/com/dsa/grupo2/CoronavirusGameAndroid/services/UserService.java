package com.dsa.grupo2.CoronavirusGameAndroid.services;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Credentials;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;

import com.dsa.grupo2.CoronavirusGameAndroid.models.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface UserService {

    @POST("user/adduser")
    Call<User> register(@Body User user);

    @POST("user/login")
    Call<String> logIn(@Body Credentials cred);


    @GET("user/{name}") 
    Call<User> getUser(@Path("name") String name);

    @GET("user/search/{id}")
    Call<User> getUserByID(@Path("id") String id);

    @PUT("user/{id}")
    Call<User> updateUser(@Body User user,@Path(value="id",encoded=true) String oldId);

    @POST("user/logout")
    Call<Void> logOut(@Body Token token);

    @Multipart
    @POST("user/image/{userid}")
    Call<Void> uploadImage(@Part MultipartBody.Part file, @Path("userid") String userId);
}
