package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Level;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;

import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter {

    private List<Level> levels;
    public TextView lvlText;
    public int unlockedLevels;
    public View layout;
    public int lockedLevel = 0;
    public int unlockedLevel = 1;


    public class UnlockedLevelHolder extends RecyclerView.ViewHolder {

        public UnlockedLevelHolder(View v) {
            super(v);
            layout = v;
            lvlText = (TextView) v.findViewById(R.id.level_number);
        }

        void bind(Level level) {
            lvlText.setText(String.valueOf(level.getLvlNumber()));
        }
    }

    public class LockedLevelHolder extends RecyclerView.ViewHolder {
        public LockedLevelHolder(View v) {
            super(v);
            layout = v;
        }

        void bind(Level level) {

        }
    }

    public void add(int position, Level item) {
        levels.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        levels.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < unlockedLevels)
            return unlockedLevel;
        else
            return lockedLevel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if (viewType == unlockedLevel) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_row, parent, false);
            return new UnlockedLevelHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locked_level_row, parent, false);
            return new LockedLevelHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == unlockedLevel)
            ((UnlockedLevelHolder) holder).bind(levels.get(position));
        else ((LockedLevelHolder) holder).bind(levels.get(position));

    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public LevelAdapter(List<Level> lvls, int completedLevels) {
        levels = lvls;
        unlockedLevels = completedLevels + 1;
    }
}
