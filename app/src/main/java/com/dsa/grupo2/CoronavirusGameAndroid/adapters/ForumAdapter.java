package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.ApiConn;
import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.Message;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter {
    private List<Message> messages;
    public int messageSent = 0;
    public int messageReceived = 1;

    public class ReceivedViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtMessage;
        public View layout;

        public ReceivedViewHolder(View v) {
            super(v);
            layout = v;
            txtName = (TextView) v.findViewById(R.id.textReceivedName);
            txtMessage = (TextView) v.findViewById(R.id.textReceivedContent);
        }

        void bind(Message message) {
            txtName.setText(message.getUsername());
            txtMessage.setText(message.getContent());
        }
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {
        public TextView txtMessage;
        public View layout;

        public SentViewHolder(View v) {
            super(v);
            layout = v;
            txtMessage = (TextView) v.findViewById(R.id.sentMessageContent);
        }

        void bind(Message message) {
            txtMessage.setText(message.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = messages.get(position);
        if (msg.getUsername().equals(ApiConn.getInstace().getUsername()))
            return messageSent;
        else
            return messageReceived;
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

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == messageSent) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_row, parent, false);
            return new SentViewHolder(view);
        }
        else if (viewType == messageReceived) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_received_row, parent, false);
            return new ReceivedViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        if (holder.getItemViewType() == messageSent) {
            ((SentViewHolder) holder).bind(message);
        }
        else if (holder.getItemViewType() == messageReceived) {
            ((ReceivedViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
