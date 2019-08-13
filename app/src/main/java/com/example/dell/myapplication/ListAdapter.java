package com.example.dell.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;


public class ListAdapter extends RecyclerView.Adapter {

public DatabaseReference mDatabase;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_capteur,parent,false);
       ListViewHolder vh = new ListViewHolder(view);
       return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
                ((ListViewHolder) holder).bindView(position);
        if (position==0) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("systeme");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue().toString().equals("active")){
                    ((ListViewHolder) holder).degre.setText("OFF");}
                    else{((ListViewHolder) holder).degre.setText("ON");}
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
                if (position==1) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("temperature");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ((ListViewHolder) holder).degre.setText(dataSnapshot.getValue().toString()+"°C");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (position==2) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("humidite");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ((ListViewHolder) holder).degre.setText(dataSnapshot.getValue().toString()+"%");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (position==3) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("gaz");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue().toString().equals("0")){
                    ((ListViewHolder) holder).degre.setText("Non");
                }
                else{
                        ((ListViewHolder) holder).degre.setText("Oui");
                    }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (position==4) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("mouvement");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {if(dataSnapshot.getValue().toString().equals("non")){
                    ((ListViewHolder) holder).degre.setText("Non");
                }
                else{
                    ((ListViewHolder) holder).degre.setText("Oui");
                }}

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (position==5) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("ventilateur");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getValue().toString().equals("0")){
                        ((ListViewHolder) holder).degre.setText("OFF");
                    }
                    else{
                        ((ListViewHolder) holder).degre.setText("ON");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (position==6) {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Porte");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue().toString().equals("Ouvert")){
                        ((ListViewHolder) holder).degre.setText("Ouverte");
                    }
                    else{
                        ((ListViewHolder) holder).degre.setText("Fermée");
                    }}
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return Adapter.title.length;

    }

   public class  ListViewHolder extends RecyclerView.ViewHolder{
       DatabaseReference mDatabase;


        public TextView nom;
        public ImageView image;
        public TextView degre;


        public ListViewHolder(View itemView) {
            super(itemView);

            nom = (TextView) itemView.findViewById(R.id.textView10);
            image = (ImageView) itemView.findViewById(R.id.imageView);
             degre = (TextView) itemView.findViewById(R.id.textViewTitle);
        }


       public void bindView(int position){
           nom.setText(Adapter.title[position]);
           image.setImageResource(Adapter.picture[position]);

           }

   }}



