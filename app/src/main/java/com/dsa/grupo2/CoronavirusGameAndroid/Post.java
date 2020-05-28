package com.dsa.grupo2.CoronavirusGameAndroid;

import dsa.grupo2.UserDaoImp;
import dsa.grupo2.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Post {
    UsuarioService usuarioService;


    private void singIn(User u){
        UserDaoImp db = new UserDaoImp();
        db.addUser(u.getName(),u.getEmail(),u.getPassword());

        Call<ResponseBody> call = usuarioService.singIn(u);
    }
}
