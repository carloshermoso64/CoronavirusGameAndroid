package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumMainAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumThreadAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumMessage;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumThreadActivity extends AppCompatActivity {

    List<ForumMessage> messages;
    LinearLayoutManager layoutManager;
    ForumThreadAdapter messageAdapter;
    RecyclerView messageViewer;
    SwipeRefreshLayout refresher;
    String threadId;
    ProgressBar loader;
    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_thread);
        Bundle extras = getIntent().getExtras();
        threadId = extras.getString("threadId");
        String title = extras.getString("threadName");
        loader = findViewById(R.id.progressBar3);
        messageText = findViewById(R.id.ForumThreadText);

        TextView titleText = findViewById(R.id.ForumThreadName);
        messageViewer = findViewById(R.id.forumThreadViewer);
        messageViewer.setVisibility(View.GONE);
        refresher = findViewById(R.id.forumThreadRefresh);
        refresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMessages();
            }
        });
        titleText.setText(title);

        Call<List<ForumMessage>> messagesCall = ApiConn.getInstace().getForumService().getMessagesOfThread(threadId);
        messagesCall.enqueue(new Callback<List<ForumMessage>>() {
            @Override
            public void onResponse(Call<List<ForumMessage>> call, Response<List<ForumMessage>> response) {
                if (response.code() == 200) {
                    messages = response.body();
                    setMessages();
                    loader.setVisibility(View.GONE);
                    messageViewer.setVisibility(View.VISIBLE);
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

    public void refreshMessages() {
        Call<List<ForumMessage>> messagesCall = ApiConn.getInstace().getForumService().getMessagesOfThread(threadId);
        messagesCall.enqueue(new Callback<List<ForumMessage>>() {
            @Override
            public void onResponse(Call<List<ForumMessage>> call, Response<List<ForumMessage>> response) {
                if (response.code() == 200) {
                    messages.clear();
                    messages.addAll(response.body());
                    messageAdapter.notifyDataSetChanged();
                    refresher.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ForumMessage>> call, Throwable t) {

            }
        });
    }

    public void postMessage(View v) {
        String content = messageText.getText().toString();
        MultipartBody.Part authorPart = MultipartBody.Part.createFormData("author", ApiConn.getInstace().getUsername());
        MultipartBody.Part contentPart = MultipartBody.Part.createFormData("content", content);
        if (!content.equals("")) {
            Call<Void> sendMessage = ApiConn.getInstace().getForumService().postMessage(threadId, authorPart, contentPart);
            sendMessage.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 201) {
                        refreshMessages();
                        messageText.setText("");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }

            });
        }
        else {
            Toast.makeText(getApplicationContext(), "You must write something to post a message", Toast.LENGTH_LONG).show();
        }
    }
}