package com.dsa.grupo2.CoronavirusGameAndroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.BestLevel;
import com.dsa.grupo2.CoronavirusGameAndroid.services.BestLevelService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RankingTabUserLevels#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RankingTabUserLevels extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BestLevelService bestLevelService;

    private List<BestLevel> bestLevelList = new ArrayList<>();

    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    public RankingTabUserLevels() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tabUserLevels.
     */
    // TODO: Rename and change types and number of parameters
    public static RankingTabUserLevels newInstance(String param1, String param2) {
        RankingTabUserLevels fragment = new RankingTabUserLevels();
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
        Call<List<BestLevel>> userScores = bestLevelService.userScores("pep");//CAMBIAR!!!!!!!!!

        userScores.enqueue(new Callback<List<BestLevel>>() {
            @Override
            public void onResponse(Call<List<BestLevel>> call, Response<List<BestLevel>> response) {
                if (response.code()== 201){
                    bestLevelList = response.body();
                    loadResult();
                }else if (response.code()==404){
                    Toast.makeText(context,"User not found",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Unexpected error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<BestLevel>> call, Throwable t) {
                Toast.makeText(context,"Error retrieving best level list",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tab_user_levels, container, false);

        recyclerView = view.findViewById(R.id.recycleList);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapterRanking(bestLevelList);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private void loadResult(){
        mAdapter = new MyAdapterRanking(bestLevelList);
        recyclerView.setAdapter(mAdapter);

    }

}