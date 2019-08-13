package com.example.dell.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;


public class humidite extends Fragment {
DatabaseReference mDatabase1;
ArcProgress humidite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.humidite, container, false);
        humidite=(ArcProgress) rootview.findViewById(R.id.arc_progress1);
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("humidite");
        if (!isNetworkAvailablee(getContext())) {
            Toast.makeText(getContext(), "No connection ", Toast.LENGTH_LONG).show();
        }
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                humidite.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return rootview;
    }
}




