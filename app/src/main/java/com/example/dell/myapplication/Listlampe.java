package com.example.dell.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;


public class Listlampe extends AppCompatActivity {
DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lampe);
        setTitle("Lampes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        Button arret=(Button) findViewById(R.id.button3);
        Button allumer=(Button) findViewById(R.id.button2);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        arret.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (!isNetworkAvailablee(getApplicationContext())) {
                   Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
               }else {
               mDatabase.child("lampe4").setValue("0");
               mDatabase.child("lampe1").setValue("0");
               mDatabase.child("lampe2").setValue("0");
               mDatabase.child("lampe3").setValue("0");}
           }
       });
        allumer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                mDatabase.child("lampe4").setValue("1");
                mDatabase.child("lampe1").setValue("1");
                mDatabase.child("lampe2").setValue("1");
                mDatabase.child("lampe3").setValue("1");}
            }
        });


        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.lampes);
        lampeadapter lampeadapter=new lampeadapter();
        recyclerView.setAdapter(lampeadapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



    }}





