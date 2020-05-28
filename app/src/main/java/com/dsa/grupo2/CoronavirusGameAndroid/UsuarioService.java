package com.dsa.grupo2.CoronavirusGameAndroid;
import dsa.grupo2.UserDaoImp;
import dsa.grupo2.models.User;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("/user/adduser")
    Call<ResponseBody> singIn(@Body User user);


}
