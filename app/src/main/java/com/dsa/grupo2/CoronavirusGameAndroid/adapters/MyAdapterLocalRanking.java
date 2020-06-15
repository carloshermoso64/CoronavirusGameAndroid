package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.BestLevel;

import java.util.List;

public class MyAdapterLocalRanking extends RecyclerView.Adapter<MyAdapterLocalRanking.ViewHolder> {

    private List<BestLevel> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView score;
        public TextView time;
        public View layout;
        public TextView numeroNivel;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            score = (TextView) v.findViewById(R.id.scoreRanking);
            numeroNivel = (TextView) v.findViewById(R.id.nivelRanking);
            time = (TextView) v.findViewById(R.id.timeRanking);
        }
    }

    public void add(int position, BestLevel item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapterLocalRanking(List<BestLevel> myDataset) {
        values = myDataset;
        LayoutInflater inflater;
    }

    @Override
    public MyAdapterLocalRanking.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.itemlayout_userlevel_ranking, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final int bestScore = values.get(position).getBestScore();
        holder.score.setText(String.valueOf(bestScore));

        final int levelNumber = values.get(position).getLevelNumber();
        holder.numeroNivel.setText(String.valueOf(levelNumber));

        final int bestTime = values.get(position).getBestTime();
        holder.time.setText(String.valueOf(bestTime));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
