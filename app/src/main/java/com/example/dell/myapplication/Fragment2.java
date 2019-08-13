package com.example.dell.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Fragment2 extends Fragment {
    SeekBar volt;
    TextView textview;
    TextView textview1;
    DatabaseReference mDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment2 , container, false);
        volt =   (SeekBar) rootView.findViewById(R.id.voltage);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        textview1 =(TextView) rootView.findViewById(R.id.textview1);

        volt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                mDatabase.child("ventilateur").setValue(String.valueOf(progress));
                textview1.setText(String.valueOf(progress)+"V");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });





        return rootView;
    }

}
