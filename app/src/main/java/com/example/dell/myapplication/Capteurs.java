package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Capteurs extends AppCompatActivity {
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    DatabaseReference mDatabase2;
    ArcProgress temp;
    ArcProgress  hum;
    ArcProgress gaz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capteurs);
        setTitle("Capteurs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        temp=(ArcProgress) findViewById(R.id.arc_progress);
        hum=(ArcProgress) findViewById(R.id.arc_progress1);
        gaz=(ArcProgress) findViewById(R.id.arc_progress2);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("temperature");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                temp.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase1 = FirebaseDatabase.getInstance().getReference().child("gaz");
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gaz.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase2 = FirebaseDatabase.getInstance().getReference().child("humidite");
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hum.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
