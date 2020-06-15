package com.dsa.grupo2.CoronavirusGameAndroid.services;

import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumThread;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ForumService {
    @GET("forum/threads")
    Call<List<ForumThread>> getAllThreads();


    @Multipart
    @POST("forum/newthread")
    Call<Void> postNewThread(@Part MultipartBody.Part author,@Part MultipartBody.Part name,@Part MultipartBody.Part content);
}
