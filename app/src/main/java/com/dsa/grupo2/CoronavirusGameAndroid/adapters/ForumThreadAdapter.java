package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumMessage;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumThread;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.CircleTransform;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ForumThreadAdapter extends RecyclerView.Adapter {
    private List<ForumMessage> messages;

    String pattern = "dd/MM/yyyy hh:mm";
    DateFormat df = new SimpleDateFormat(pattern);


    public class ForumMessageHolder extends RecyclerView.ViewHolder {
        public TextView date, author, content;
        public ImageView pic;
        public View layout;

        public ForumMessageHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            author = (TextView) itemView.findViewById(R.id.forumThreadRowName);
            date = (TextView) itemView.findViewById(R.id.forumThreadRowDate);
            content = (TextView) itemView.findViewById(R.id.forumThreadRowContent);
            pic = (ImageView) itemView.findViewById(R.id.forumThreadRowProfile);
        }

        void bind(ForumMessage message) {
            author.setText(message.getAuthor());
            content.setText(message.getContent());
            date.setText(df.format(message.getPosted()));
            Picasso.get().load("http://"+ ApiConn.getInstace().getIP()+":8080/dsaApp/user/image/"+ message.getAuthorId()).fit().transform(new CircleTransform())
                    .placeholder(R.drawable.defaultprofile)
                    .memoryPolicy(MemoryPolicy.NO_CACHE )
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .error(R.drawable.defaultprofile).into(pic);
        }
    }
    public void add(int position, ForumMessage item) {
        messages.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        messages.remove(position);
        notifyItemRemoved(position);
    }

    public ForumThreadAdapter(List<ForumMessage> myDataset) {
        messages = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_message_row, parent, false);

        return new ForumMessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ForumMessage message = messages.get(position);
        ((ForumThreadAdapter.ForumMessageHolder) holder).bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
