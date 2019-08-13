package com.example.dell.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Securite extends AppCompatActivity {
DatabaseReference mDatabase;
    TextView sys;
    Switch sw;
    TextView email;
    CheckBox not;
    TextView keypad;
    CheckBox son;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_securite);
        setTitle("Securité");
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email=(TextView) findViewById(R.id.textView15);
        sys= findViewById(R.id.textView13);
        sw= findViewById(R.id.switch2);
        son=(CheckBox) findViewById(R.id.switch3);
        not=(CheckBox) findViewById(R.id.checkBox);

        mDatabase=FirebaseDatabase.getInstance().getReference();
        mDatabase.child("systeme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("active")){
                    sw.setChecked(true);
                    sys.setText("Désactivé");}
                else {
                    sw.setChecked(false);
                    sys.setText("Activé");}}
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("sons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue().toString().equals("activé")) {
                    son.setChecked(true);
                } else {
                    son.setChecked(false);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDatabase.child("notifs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("activé")){
                    not.setChecked(true);
                    }
                else {
                    not.setChecked(false);
                   }}
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                if(b)
                {
                    mDatabase.child("systeme").setValue("active");
                    sys.setText("Désactivé");
                    mDatabase.child("alarme").setValue("0");
                }
                else {
                    mDatabase.child("systeme").setValue("desactive");
                    sys.setText("Activé");
                }}
            }
        });
        son.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean a) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                if(a)
                {
                    mDatabase.child("sons").setValue("activé");
                }
                else {
                    mDatabase.child("sons").setValue("Désactivé");
                }}
            }
        });
        not.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean c) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                if(c)
                {
                    mDatabase.child("notifs").setValue("activé");
                }
                else {
                    mDatabase.child("notifs").setValue("Désactivé");
                }}
            }
        });
    }
}