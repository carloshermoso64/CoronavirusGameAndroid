package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.BestLevelTO;

import java.util.List;

public class MyAdapterGlobalRanking extends RecyclerView.Adapter<MyAdapterGlobalRanking.ViewHolder>{
    private List<BestLevelTO> valuesBestLevel;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView score;
        public TextView time;
        public View layout;
        public TextView numeroNivel;
        public TextView user;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            score = (TextView) v.findViewById(R.id.scoreRanking);
            numeroNivel = (TextView) v.findViewById(R.id.nivelRanking);
            time = (TextView) v.findViewById(R.id.timeRanking);
            user = (TextView) v.findViewById(R.id.userRanking);
        }
    }

    public void add(int position, BestLevelTO bestLevel) {
        valuesBestLevel.add(position, bestLevel);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        valuesBestLevel.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapterGlobalRanking(List<BestLevelTO> bestLevels) {
        valuesBestLevel = bestLevels;
        LayoutInflater inflater;
    }

    @Override
    public MyAdapterGlobalRanking.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.itemlayout_globallevel_ranking, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapterGlobalRanking.ViewHolder holder, final int position) {
        final int bestScore = valuesBestLevel.get(position).getBestScore();
        holder.score.setText(String.valueOf(bestScore));

        final String bestUser = valuesBestLevel.get(position).getUsername();
        holder.user.setText(bestUser);

        final int levelNumber = valuesBestLevel.get(position).getLevelNumber();
        holder.numeroNivel.setText(String.valueOf(levelNumber));

        final int bestTime = valuesBestLevel.get(position).getBestTime();
        holder.time.setText(String.valueOf(bestTime));
    }

    @Override
    public int getItemCount() {
        return valuesBestLevel.size();
    }

}
