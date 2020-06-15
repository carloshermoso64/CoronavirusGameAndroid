package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.MyAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public String currentId;
    final LoadingDialog loadingDialog= new LoadingDialog(EditProfileActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        loadingDialog.startLoadingDialog();
        TextView level =findViewById(R.id.levelTextView);
        TextView exp = findViewById(R.id.expTextView);
        ImageView avatar = findViewById(R.id.avatarImageView);
        Button changeDataBtn = findViewById(R.id.changeDataBtn);
        Button commitChangesBtn = findViewById(R.id.commitChangesDataBtn);
        TextInputEditText editTextUsername = findViewById(R.id.editTextUsername);
        TextInputEditText editTextPassword = findViewById(R.id.editTextPassword);
        TextInputEditText editTextEmail = findViewById(R.id.editTextEmail);
        commitChangesBtn.setVisibility(View.GONE);
        editTextEmail.setVisibility(View.GONE);
        editTextPassword.setVisibility(View.GONE);
        editTextUsername.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        Context context = getApplicationContext();
        refreshUserData(ApiConn.getInstace().getUsername(),level,exp,avatar,context);
        loadingDialog.dismissDialog();

        editTextUsername.setText(ApiConn.getInstace().getUsername());
        editTextPassword.setText(ApiConn.getInstace().getPassword());
        editTextEmail.setText(ApiConn.getInstace().getEmail());


        changeDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextUsername.setVisibility(View.VISIBLE);
                editTextPassword.setVisibility(View.VISIBLE);
                editTextEmail.setVisibility(View.VISIBLE);
                commitChangesBtn.setVisibility(View.VISIBLE);
            }
        });
        commitChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                User u = new User(editTextUsername.getText().toString(), editTextEmail.getText().toString(), editTextPassword.getText().toString());
                Call<User> newUser = ApiConn.getInstace().getUserService().updateUser(u,ApiConn.getInstace().getUserId());
                newUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code()==201) {
                            User u = response.body();
                            refreshUserData(u.getName(),level,exp,avatar,context);
                            loadingDialog.dismissDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismissDialog();
                    }
                });
                loadingDialog.dismissDialog();
            }
        });



    }

    public void refreshUserData(String name,TextView level,TextView exp,ImageView avatar,Context context){
        Call<User> myUser = ApiConn.getInstace().getUserService().getUser(name);
        myUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code()==201){
                    User user = response.body();
                    currentId = user.getId();
                    level.setText(currentId);
                    exp.setText("Exp "+user.getExp());
                    String url = "https://media-exp1.licdn.com/dms/image/C4D03AQGFVAhiI718AA/profile-displayphoto-shrink_200_200/0?e=1596672000&v=beta&t=SncF6DpeedyPM19_D-l3nFGr_GAcH7wsu74bYYTvhVs";
                    Picasso.get().load(url).resize(500,500).centerCrop().into(avatar);
                    List<String> input = new ArrayList<>();
                    input.add(user.getName());
                    input.add(user.getPassword());
                    input.add(user.getEmail());
                    input.add(user.toStringExp());
                    input.add(user.toStringLevel());
                    recyclerView.setHasFixedSize(true);
                    // use a linear layout manager
                    layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter = new MyAdapter(input);
                    recyclerView.setAdapter(mAdapter);

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }
}