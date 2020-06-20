package com.dsa.grupo2.CoronavirusGameAndroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsa.grupo2.CoronavirusGameAndroid.ForumThreadActivity;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumMessage;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.CircleTransform;
import com.dsa.grupo2.CoronavirusGameAndroid.R;
import com.dsa.grupo2.CoronavirusGameAndroid.models.ForumThread;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumMainAdapter extends RecyclerView.Adapter{

    private List<ForumThread> threads;

    String pattern = "dd/MM/yyyy hh:mm";
    DateFormat df = new SimpleDateFormat(pattern);

    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public class forumHolder extends RecyclerView.ViewHolder {
        public TextView lastMessage, author, name;
        public ImageView pic;
        public View layout;

        public forumHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            name = (TextView) itemView.findViewById(R.id.forumRowName);
            author = (TextView) itemView.findViewById(R.id.forumRowAuthor);
            lastMessage = (TextView) itemView.findViewById(R.id.forumRowLastMessage);
            pic = (ImageView) itemView.findViewById(R.id.forumProfile);
        }

        void bind(ForumThread thread) {
            author.setText(thread.getAuthor());
            name.setText(thread.getName());
            lastMessage.setText("Last message: " + df.format(thread.getLastMessage()));
            Picasso.get().load("http://"+ ApiConn.getInstace().getIP()+":8080/dsaApp/user/image/"+ thread.getAuthorId()).fit().transform(new CircleTransform())
                    .placeholder(R.drawable.defaultprofile)
                    .memoryPolicy(MemoryPolicy.NO_CACHE )
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .error(R.drawable.defaultprofile).into(pic);
        }
    }

    public void add(int position, ForumThread item) {
        threads.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        threads.remove(position);
        notifyItemRemoved(position);
    }

    public ForumMainAdapter(List<ForumThread> myDataset) {
        threads = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_row, parent, false);

        return new forumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ForumThread thread = threads.get(position);
        ((ForumMainAdapter.forumHolder) holder).bind(thread);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClick.onItemClick(position);

            }


        });

    }

    @Override
    public int getItemCount() {
        return threads.size();
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
}
