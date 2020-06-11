package com.dsa.grupo2.CoronavirusGameAndroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.models.BestLevel;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;

import java.util.List;

public class MyAdapterUserRanking extends RecyclerView.Adapter<MyAdapterUserRanking.ViewHolder> {

    private List<User> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView user;
        public TextView level;
        public View layout;
        public TextView exp;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            user = (TextView) v.findViewById(R.id.userRanking);
            level = (TextView) v.findViewById(R.id.levelRanking);
            exp = (TextView) v.findViewById(R.id.expRanking);
        }
    }

    public void add(int position, User item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapterUserRanking(List<User> myDataset) {
        values = myDataset;
        LayoutInflater inflater;
    }

    @Override
    public MyAdapterUserRanking.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.itemlayout_users_ranking, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String user = values.get(position).getName();
        holder.user.setText(String.valueOf(user));

        final int level = values.get(position).getLevel();
        holder.level.setText(String.valueOf(level));

        final int exp = values.get(position).getExp();
        holder.exp.setText(String.valueOf(exp));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
