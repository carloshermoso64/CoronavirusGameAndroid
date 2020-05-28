package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import dsa.grupo2.models.User;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.lang3.RandomStringUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btLogin = (Button) findViewById(R.id.btLogin);
        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://localhost:8080/dsaApp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                UsuarioService service = retrofit.create(UsuarioService.class);

                User u= new User("carlitos","carlosgmail","1234");

                Call<ResponseBody> response = service.singIn(u);

                response.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

            }
        });
    }
}
