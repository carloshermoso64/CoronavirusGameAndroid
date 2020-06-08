package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.BestLevel;
import com.dsa.grupo2.CoronavirusGameAndroid.services.BestLevelService;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankingActivity extends AppCompatActivity {
    final LoadingDialog loadingDialog = new LoadingDialog(RankingActivity.this);

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabUserLevels, tabGlobalLevels, tabUsers;
    private RankingPageAdapter rankingPageAdapter;

    private BestLevelService bestLevelService;

    private Context context;

    private List<BestLevel> bestLevelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        loadingDialog.startLoadingDialog();

        context = getApplicationContext();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabUserLevels = (TabItem) findViewById(R.id.tabUserLevels);
        tabGlobalLevels = (TabItem) findViewById(R.id.tabGlobalLevels);
        tabUsers = (TabItem) findViewById(R.id.tabUsers);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        rankingPageAdapter = new RankingPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), context);
        viewPager.setAdapter(rankingPageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    rankingPageAdapter.notifyDataSetChanged();
                }else if (tab.getPosition()==1){
                    rankingPageAdapter.notifyDataSetChanged();
                }else if(tab.getPosition()==2){
                    rankingPageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        loadingDialog.dismissDialog();
    }

}