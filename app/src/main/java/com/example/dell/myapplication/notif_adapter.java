package com.example.dell.myapplication;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class notif_adapter extends RecyclerView.Adapter<notif_adapter.MyViewHolder> {

    private List<Notif> notifList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year;
            public ImageView    genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewTitle);
            genre = (ImageView) view.findViewById(R.id.imageView);
            year = (TextView) view.findViewById(R.id.textView10);
        }
    }


    public notif_adapter(List<Notif> notifList) {
        this.notifList = notifList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notifs, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notif notif = notifList.get(position);
        holder.title.setText(notif.getTitle());
        holder.genre.setImageResource(notif.getGenre());
        holder.year.setText(notif.getYear());
    }

    @Override
    public int getItemCount() {
        return notifList.size();
    }
}