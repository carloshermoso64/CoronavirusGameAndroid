package com.dsa.grupo2.CoronavirusGameAndroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.adapters.MyAdapterUserRanking;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.dsa.grupo2.CoronavirusGameAndroid.services.BestLevelService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingTabUsers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingTabUsers extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BestLevelService bestLevelService;

    private List<User> bestUserList = new ArrayList<>();

    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    public RankingTabUsers() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tabUsers.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingTabUsers newInstance(String param1, String param2) {
        RankingTabUsers fragment = new RankingTabUsers();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        context = getActivity();

        sharedPref = context.getSharedPreferences("coronavirusgame", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        bestLevelService = ApiConn.getInstace().getBestLevelService();
        Call<List<User>> userRanking= bestLevelService.userRanking();

        userRanking.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code()== 201){
                    bestUserList = response.body();
                    loadResult();
                }else if (response.code()==404){
                    Toast.makeText(context,"User not found",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Unexpected error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(context,"Error retrieving best user list",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_users, container, false);

        recyclerView = view.findViewById(R.id.recycleList);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapterUserRanking(bestUserList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private void loadResult(){
        mAdapter = new MyAdapterUserRanking(bestUserList);
        recyclerView.setAdapter(mAdapter);
    }
}