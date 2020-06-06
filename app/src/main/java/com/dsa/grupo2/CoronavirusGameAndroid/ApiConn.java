package com.dsa.grupo2.CoronavirusGameAndroid;

import com.dsa.grupo2.CoronavirusGameAndroid.services.ForumService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiConn {

    private static ApiConn instace;
    private HttpLoggingInterceptor interceptor;
    private OkHttpClient client;
    private Retrofit retrofit;

    private UserService userService;
    private ForumService forumService;
    private String userToken;
    private String username;

    public static ApiConn getInstace() {
        if (instace == null)
            instace = new ApiConn();
        return instace;
    }

    private ApiConn() {
        this.interceptor = new HttpLoggingInterceptor();
        this.interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.userService = retrofit.create(UserService.class);
        this.forumService = retrofit.create(ForumService.class);
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public UserService getUserService() {
        return userService;
    }

    public ForumService getForumService() {
        return forumService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
