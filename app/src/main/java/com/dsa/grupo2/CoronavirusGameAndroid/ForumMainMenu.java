package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.ForumMainAdapter;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumThread;
import com.dsa.grupo2.CoronavirusGameAndroid.services.ForumService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumMainMenu extends AppCompatActivity implements AddThreadDialog.NoticeDialogListener, ForumMainAdapter.OnItemClicked{

    ForumService forumService = ApiConn.getInstace().getForumService();
    ArrayList<ForumThread> allThreads;
    ForumMainAdapter forumAdapter;
    RecyclerView forumViewer;
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_main_menu);



        forumViewer = findViewById(R.id.forumThreadViewer);
        swipeRefreshLayout = findViewById(R.id.forumSwipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshThreads();
            }
        });

        loadThreadsOnView();

    }

    public void loadThreadsOnView() {

        Call<List<ForumThread>> forumCall = forumService.getAllThreads();

        forumCall.enqueue(new Callback<List<ForumThread>>() {
            @Override
            public void onResponse(Call<List<ForumThread>> call, Response<List<ForumThread>> response) {
                if (response.code() == 200); {
                    allThreads = (ArrayList<ForumThread>) response.body();
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    forumViewer.setLayoutManager(layoutManager);
                    forumAdapter = new ForumMainAdapter(allThreads);
                    forumViewer.setAdapter(forumAdapter);
                    forumAdapter.setOnClick(ForumMainMenu.this);
                }
            }

            @Override
            public void onFailure(Call<List<ForumThread>> call, Throwable t) {

            }
        });
    }

    public void refreshThreads() {

        Call<List<ForumThread>> forumCall = forumService.getAllThreads();
        forumCall.enqueue(new Callback<List<ForumThread>>() {
            @Override
            public void onResponse(Call<List<ForumThread>> call, Response<List<ForumThread>> response) {
                if (response.code() == 200) {
                    allThreads.clear();
                    allThreads.addAll(response.body());
                    forumAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<List<ForumThread>> call, Throwable t) {

            }
        });
    }

    public void addThread(View v) {
        AddThreadDialog d = new AddThreadDialog();
        d.show(getSupportFragmentManager(),"newdialog");
    }

    @Override
    public void onDialogThreadAdded(DialogFragment dialog) {
        refreshThreads();
        Toast.makeText(getApplicationContext(), "Thread added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorAddingThread(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getApplicationContext(), ForumThreadActivity.class);
        intent.putExtra("threadId", allThreads.get(position).getId());
        intent.putExtra("threadName", allThreads.get(position).getName());
        startActivity(intent);
    }
}