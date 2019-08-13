package com.example.dell.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;


public class RecyclerViewAdapter extends RecyclerView.Adapter  {

    public ArrayList mValues;
    Context mContext;


    public RecyclerViewAdapter(Listacceuil context, ArrayList values, Listacceuil listacceuil) {

        mValues = values;
        mContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        DataModel item;
         public ArrayList mValues;

        private ViewHolder(View v) {

            super(v);



            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);


        }
        public void onClick(View view) {

            }


        public void bindView(int position) {    textView.setText(DataModel.title[position]);
            imageView.setImageResource(DataModel.picture[position]);
            relativeLayout.setBackgroundColor(Color.parseColor(DataModel.color[position]));
        }
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.listacceuil, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder) .bindView(position);

    }

    @Override
    public int getItemCount() {

        return DataModel.title.length;
    }



}



