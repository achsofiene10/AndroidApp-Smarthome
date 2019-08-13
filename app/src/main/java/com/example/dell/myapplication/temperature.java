package com.example.dell.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;


public class temperature extends Fragment {

ArcProgress temperature;
    DatabaseReference mDatabase1;
    DatabaseReference mDatabase2;
    ImageView plus;
    ImageView moins;
    TextView temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.temperature, container, false);
        temperature=(ArcProgress) rootview.findViewById(R.id.arc_progress);
        plus=rootview.findViewById(R.id.plus);
        moins=rootview.findViewById(R.id.moins);

        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("tempcible");
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("temperature");
        if (!isNetworkAvailablee(getContext())) {
            Toast.makeText(getContext(), "No connection ", Toast.LENGTH_LONG).show();
        }else {
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temperature.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        temp= rootview.findViewById(R.id.textView19);
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });}
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getContext())) {
                    Toast.makeText(getContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                Integer x=Integer.valueOf(temp.getText().toString());
                x=x+1;
                temp.setText(x.toString());
                mDatabase2.setValue(x.toString());}
            }
        });
        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getContext())) {
                    Toast.makeText(getContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                Integer x=Integer.valueOf(temp.getText().toString());
                x=x-1;
                temp.setText(x.toString());
                mDatabase2.setValue(x.toString());}
            }
        });

    return rootview;
    }
}
