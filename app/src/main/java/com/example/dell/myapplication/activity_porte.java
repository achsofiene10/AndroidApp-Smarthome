package com.example.dell.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class activity_porte extends AppCompatActivity {
    ImageView lock;
    ImageView unlock;
    Switch s;

    TextView textview;
    String chporte;

    AVLoadingIndicatorView avi;
    DatabaseReference mDatabase;
    boolean active = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porte);
        setTitle("Porte");
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s = (Switch) findViewById(R.id.switch1);
        unlock = (ImageView) findViewById(R.id.imgLogo);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        lock = (ImageView) findViewById(R.id.imgLogo1);
        textview = (TextView) findViewById(R.id.textView3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String chporte;
        avi.hide();
        unlock.setVisibility(View.GONE);
        lock.setVisibility(View.VISIBLE);
        boolean b = (s).isChecked();
        mDatabase.child("Porte").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue().toString().equals("Ouvert")) {
                    s.setChecked(true);
                    lock.setVisibility(View.GONE);
                    unlock.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();

                } else {

                    if (b) {
                        avi.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                lock.setVisibility(View.GONE);
                                unlock.setVisibility(View.VISIBLE);
                                mDatabase.child("Porte").setValue("Ouvert");
                                textview.setText(" Porte Ouverte");
                                avi.hide();
                            }
                        }, 3000);


                    } else {
                        avi.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                unlock.setVisibility(View.GONE);
                                lock.setVisibility(View.VISIBLE);
                                mDatabase.child("Porte").setValue("Fermer");
                                textview.setText(" Porte Fermée");
                                avi.hide();
                            }
                        }, 3000);
                    }
                }
            }
        });

    }

}


