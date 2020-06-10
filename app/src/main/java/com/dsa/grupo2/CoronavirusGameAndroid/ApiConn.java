package com.dsa.grupo2.CoronavirusGameAndroid;

import com.dsa.grupo2.CoronavirusGameAndroid.services.ShopService;
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
    private ShopService shopService;
    private String userToken;

    private String username;
    private String userId;

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
                .baseUrl("http://147.83.7.204:8080/dsaApp/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.userService = retrofit.create(UserService.class);
        this.shopService = retrofit.create(ShopService.class);
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

    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}