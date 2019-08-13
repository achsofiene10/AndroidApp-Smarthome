package com.example.dell.myapplication;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;


public class gaz extends Fragment {
ArcProgress gaz;
DatabaseReference mDatabase;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View rootview =inflater.inflate(R.layout.gaz, container, false);
       gaz=(ArcProgress) rootview.findViewById(R.id.arc_progress2);
        mDatabase=FirebaseDatabase.getInstance().getReference();
        mDatabase.child("gaz").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gaz.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return rootview;
    }
}