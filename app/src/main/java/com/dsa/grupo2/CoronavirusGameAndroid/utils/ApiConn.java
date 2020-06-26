package com.dsa.grupo2.CoronavirusGameAndroid.utils;


import com.dsa.grupo2.CoronavirusGameAndroid.services.ChatService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ForumService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.GameService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ShopService;

import com.dsa.grupo2.CoronavirusGameAndroid.services.BestLevelService;

import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;

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

    private ChatService chatService;

    private ShopService shopService;

    private BestLevelService bestLevelService;

    private GameService gameService;

    private ForumService forumService;

    private String userToken;
    private String username;
    private String userId;
    private String email;
    private String password;
    private String IP;

    public static ApiConn getInstace() {
        if (instace == null)
            instace = new ApiConn();
        return instace;
    }

    private ApiConn() {
        this.interceptor = new HttpLoggingInterceptor();
        this.interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.IP = "147.83.7.204";

        this.client = new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://"+ IP +":8080/dsaApp/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.userService = retrofit.create(UserService.class);
        this.chatService = retrofit.create(ChatService.class);
        this.shopService = retrofit.create(ShopService.class);
        this.bestLevelService = retrofit.create(BestLevelService.class);
        this.gameService = retrofit.create(GameService.class);
        this.forumService = retrofit.create(ForumService.class);
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getIP() {
        return IP;
    }

    public UserService getUserService() {
        return userService;
    }

    public ChatService getChatService() {
        return chatService;
	}

    public ShopService getShopService() {
        return shopService;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public BestLevelService getBestLevelService() {
        return bestLevelService;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GameService getGameService() {
        return gameService;
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}

