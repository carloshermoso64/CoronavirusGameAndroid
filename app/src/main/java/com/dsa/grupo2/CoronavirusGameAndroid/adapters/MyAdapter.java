package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> values;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            icon = v.findViewById(R.id.icon);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ViewHolder vh = holder;
        final String item = values.get(position);

        if (position==0){
            holder.txtHeader.setText("USUARIO: "+item);
            Picasso.get().load("https://image.flaticon.com/icons/png/512/16/16363.png").resize(500,500).centerCrop().into(holder.icon);
        }
        if (position==1){
            holder.txtHeader.setText(item);
            Picasso.get().load("https://imageog.flaticon.com/icons/png/512/159/159478.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF").resize(500,500).centerCrop().into(holder.icon);

    }
        if (position==2){
            holder.txtHeader.setText("CORREO: "+item);
            Picasso.get().load("https://imageog.flaticon.com/icons/png/512/95/95645.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF").resize(500,500).centerCrop().into(holder.icon);


        }
        if (position==3){
            holder.txtHeader.setText("EXPERIENCIA: "+item);
            Picasso.get().load("https://image.flaticon.com/icons/png/512/789/789202.png").resize(500,500).centerCrop().into(holder.icon);

        }
        if (position==4){
            holder.txtHeader.setText("NIVEL: "+item);
            Picasso.get().load("https://image.flaticon.com/icons/png/512/856/856970.png").resize(500,500).centerCrop().into(holder.icon);

        }
        else{
            holder.txtHeader.setText(item);
        }

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}