package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ForumService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumActivity extends AppCompatActivity {


    EditText preparedMessage;
    ArrayList<Message> messages;
    RecyclerView messagesViewer;
    ForumService forumService;
    ForumAdapter forumAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar loading;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);


        preparedMessage = findViewById(R.id.sendMessageText);
        messagesViewer = findViewById(R.id.messageViewer);
        forumService = ApiConn.getInstace().getForumService();
        loading = findViewById(R.id.forumLoading);
        refreshLayout = findViewById(R.id.refreshMessage);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<Message>> messageCall = forumService.getAllMessages();

                messageCall.enqueue(new Callback<List<Message>>() {
                    @Override
                    public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                        if (response.code() == 201) {
                            messages.clear();
                            messages.addAll(response.body()) ;
                            forumAdapter.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Message>> call, Throwable t) {

                    }
                });

            }
        });
        messagesViewer.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);

        Call<List<Message>> messageCall = forumService.getAllMessages();

        messageCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.code() == 201) {
                    messages = (ArrayList<Message>) response.body();
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    messagesViewer.setLayoutManager(layoutManager);
                    forumAdapter = new ForumAdapter(messages);
                    messagesViewer.setAdapter(forumAdapter);
                    messagesViewer.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                }

            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    public void sendMessage(View v) {

        Message msg = new Message();
        msg.setContent(preparedMessage.getText().toString());
        msg.setUsername(ApiConn.getInstace().getUsername());

        if (msg.getContent().equals("") || msg.getContent() == null) {

        }
        else {
            Call<Void> sendMessageCall = forumService.sendMessage(msg);
            sendMessageCall.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.code() == 201)
                        Toast.makeText(getApplicationContext(), "Mensaje Enviado", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}