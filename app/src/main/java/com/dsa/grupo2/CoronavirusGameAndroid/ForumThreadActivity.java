package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumMainAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumThreadAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumMessage;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumThreadActivity extends AppCompatActivity {

    List<ForumMessage> messages;
    LinearLayoutManager layoutManager;
    ForumThreadAdapter messageAdapter;
    RecyclerView messageViewer;
    SwipeRefreshLayout refresher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_thread);
        Bundle extras = getIntent().getExtras();
        String threadId = extras.getString("threadId");
        String title = extras.getString("threadName");

        TextView titleText = findViewById(R.id.ForumThreadName);
        messageViewer = findViewById(R.id.forumThreadViewer);
        refresher = findViewById(R.id.forumThreadRefresh);
        titleText.setText(title);

        Call<List<ForumMessage>> messagesCall = ApiConn.getInstace().getForumService().getMessagesOfThread(threadId);
        messagesCall.enqueue(new Callback<List<ForumMessage>>() {
            @Override
            public void onResponse(Call<List<ForumMessage>> call, Response<List<ForumMessage>> response) {
                if (response.code() == 200) {
                    messages = response.body();
                    setMessages();
                }
            }

            @Override
            public void onFailure(Call<List<ForumMessage>> call, Throwable t) {

            }
        });
    }

    public void setMessages() {
        layoutManager = new LinearLayoutManager(getApplicationContext());
        messageViewer.setLayoutManager(layoutManager);
        messageAdapter = new ForumThreadAdapter(messages);
        messageViewer.setAdapter(messageAdapter);
    }
}