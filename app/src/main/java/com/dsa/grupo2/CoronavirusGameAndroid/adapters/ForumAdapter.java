package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {
    private List<Message> messages;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtMessage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.nameText);
            txtMessage = (TextView) v.findViewById(R.id.messageText);
        }
    }

    public void add(int position, Message item) {
        messages.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        messages.remove(position);
        notifyItemRemoved(position);
    }

    public ForumAdapter(List<Message> myDataset) {
        messages = myDataset;
    }

    @Override
    public ForumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.forum_message_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ForumAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Message message = messages.get(position);
        holder.txtName.setText(message.getUsername());

        holder.txtMessage.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
