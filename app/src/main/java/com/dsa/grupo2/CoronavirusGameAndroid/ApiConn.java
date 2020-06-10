package com.dsa.grupo2.CoronavirusGameAndroid;

<<<<<<< HEAD
import com.dsa.grupo2.CoronavirusGameAndroid.services.ForumService;
=======
import com.dsa.grupo2.CoronavirusGameAndroid.services.ShopService;
>>>>>>> minimo2-carlos
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
<<<<<<< HEAD
    private ForumService forumService;
=======
    private ShopService shopService;
>>>>>>> minimo2-carlos
    private String userToken;
    private String username;

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
<<<<<<< HEAD
                .baseUrl("http://localhost:8080/dsaApp/")
=======
                .baseUrl("http://147.83.7.204:8080/dsaApp/")
>>>>>>> minimo2-carlos
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.userService = retrofit.create(UserService.class);
<<<<<<< HEAD
        this.forumService = retrofit.create(ForumService.class);
=======
        this.shopService = retrofit.create(ShopService.class);
>>>>>>> minimo2-carlos
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

<<<<<<< HEAD
    public ForumService getForumService() {
        return forumService;
=======
    public ShopService getShopService() {
        return shopService;
    }

    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
>>>>>>> minimo2-carlos
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
<<<<<<< HEAD
}
=======

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
>>>>>>> minimo2-carlos
