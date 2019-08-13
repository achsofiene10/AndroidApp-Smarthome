package com.example.dell.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Activity_ventil extends AppCompatActivity {
    SeekBar volt;
    TextView textview;
    TextView textview1;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventil);
        setTitle("Ventilateur");
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        volt =  (SeekBar) findViewById(R.id.voltage);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        textview1 =(TextView) findViewById(R.id.textview1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase.child("ventilateur").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    volt.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                    if(dataSnapshot.getValue().toString().equals("1")){
                textview1.setText("ON");}
                else{

                        textview1.setText("OFF");
                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        volt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();

                } else {
                if (progress!=0){
                    mDatabase.child("ventilateur").setValue(String.valueOf(progress));;
                    textview1.setText("ON");}
                else {
                    }
                mDatabase.child("ventilateur").setValue(String.valueOf(progress));
                textview1.setText("OFF");
            }}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

    }
}
