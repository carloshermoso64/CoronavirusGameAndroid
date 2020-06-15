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

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ChatAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ChatService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {


    EditText preparedMessage;
    ArrayList<Message> messages;
    RecyclerView messagesViewer;
    ChatService chatService;
    ChatAdapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar loading;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        preparedMessage = findViewById(R.id.sendMessageText);
        messagesViewer = findViewById(R.id.messageViewer);
        chatService = ApiConn.getInstace().getChatService();
        loading = findViewById(R.id.forumLoading);
        refreshLayout = findViewById(R.id.refreshMessage);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMessages();
                messagesViewer.scrollToPosition(messages.size()-1);
            }
        });
        messagesViewer.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        loadMessagesOnView();
    }

    public void sendMessage(View v) {

        Message msg = new Message();
        msg.setContent(preparedMessage.getText().toString());
        msg.setUsername(ApiConn.getInstace().getUsername());

        if (msg.getContent().equals("") || msg.getContent() == null) {

        }
        else {
            Call<Void> sendMessageCall = chatService.sendMessage(msg);
            sendMessageCall.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.code() == 201)
                        Toast.makeText(getApplicationContext(), "Mensaje Enviado", Toast.LENGTH_LONG).show();
                    preparedMessage.setText("");
                    loadMessagesOnView();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void loadMessagesOnView() {
        Call<List<Message>> messageCall = chatService.getAllMessages();

        messageCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.code() == 201) {

                    messages = (ArrayList<Message>) response.body();
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    messagesViewer.setLayoutManager(layoutManager);
                    chatAdapter = new ChatAdapter(messages);
                    messagesViewer.setAdapter(chatAdapter);
                    messagesViewer.scrollToPosition(messages.size()-1);
                    messagesViewer.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);

                }

            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    public void refreshMessages() {
        Call<List<Message>> messageCall = chatService.getAllMessages();

        messageCall.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.code() == 201) {
                    messages.clear();
                    messages.addAll(response.body());
                    chatAdapter.notifyDataSetChanged();
                    messagesViewer.scrollToPosition(messages.size()-1);
                    refreshLayout.setRefreshing(false);
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });

    }

}