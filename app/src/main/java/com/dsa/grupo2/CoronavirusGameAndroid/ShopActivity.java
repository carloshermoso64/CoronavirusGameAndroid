package com.dsa.grupo2.CoronavirusGameAndroid;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.Game;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ShopService;
import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {
    final LoadingDialog loadingDialog = new LoadingDialog(ShopActivity.this);
    ShopService shopService;
    UserService userService;
    String username;
    Game game;
    User user;
    String cash;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final TextView textUsername = (TextView) findViewById(R.id.textUsername);
        final TextView textCash = (TextView) findViewById(R.id.textCash);
        final TextView textOutputLifes = (TextView) findViewById(R.id.textOutputLifes);
        final TextView textOutputNeuron = (TextView) findViewById(R.id.textOutputNeuron);
        final TextView textLevel = (TextView) findViewById(R.id.textUsername);

        final CheckBox checkBoxMask = (CheckBox) findViewById(R.id.checkBoxMask);

        shopService = ApiConn.getInstace().getShopService();
        username = ApiConn.getInstace().getUsername();

        textUsername.setText(username);

        Call<User> getUser =  ApiConn.getInstace().getUserService().getUser(username);

        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.code() == 201){
                    User user = response.body();
                    userId=user.getId();

                    Call<Game> getGame =  shopService.getGame(userId);
                    getGame.enqueue(new Callback<Game>() {
                        @Override
                        public void onResponse(Call<Game> call, Response<Game> response) {
                            if (response.code() == 201) {
                                Game game = response.body();
                                int cash = game.getCash();
                                int lifes = game.getLifes();
                                int neuron = game.getNeurons();
                                int level = game.getCompletedLevels();
                                String mask = game.getMask();
                                if (mask.equals("TRUE")) {
                                    checkBoxMask.setChecked(true);
                                }
                                textLevel.setText(String.valueOf(level));
                                textCash.setText(String.valueOf(cash));
                                textOutputLifes.setText(String.valueOf(lifes));
                                textOutputNeuron.setText(String.valueOf(neuron));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"You must be registered to use the store",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Game> call, Throwable t) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Coronavirus has entered our servers and the connection has been lost",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });




    }

    public void buyNeuron(View v){
        Call<Void> getNeuron = shopService.getNeuron(userId);
        getNeuron.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                refresh();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

    public void buyMask(View v) {
        final CheckBox checkBoxMask = (CheckBox) findViewById(R.id.checkBoxMask);
        if (checkBoxMask.isChecked()) {
            Toast.makeText(getApplicationContext(), "Your mask is equipped", Toast.LENGTH_LONG).show();
        } else {
            Call<Void> getMask = shopService.getMask(userId);
            getMask.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    refresh();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
    }

    public void buyLife(View v){
        Call<Void> getLife = shopService.getLife(userId);
        getLife.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                refresh();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void refresh(){
        Call<Game> getGame =  shopService.getGame(userId);
        getGame.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                Game game = response.body();
                int cash = game.getCash();
                int lifes = game.getLifes();
                int neuron = game.getNeurons();
                String mask = game.getMask();


                final TextView textCash = (TextView) findViewById(R.id.textCash);
                final TextView textOutputLifes = (TextView) findViewById(R.id.textOutputLifes);
                final TextView textOutputNeuron = (TextView) findViewById(R.id.textOutputNeuron);
                final CheckBox checkBoxMask = (CheckBox) findViewById(R.id.checkBoxMask);

                textCash.setText(String.valueOf(cash));
                textOutputLifes.setText(String.valueOf(lifes));
                textOutputNeuron.setText(String.valueOf(neuron));
                if (mask.equals("TRUE")){
                    checkBoxMask.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

            }
        });

    }
}
