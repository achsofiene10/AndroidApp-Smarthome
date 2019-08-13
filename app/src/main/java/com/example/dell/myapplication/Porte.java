package com.example.dell.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Porte extends Fragment {
    ImageView lock ;
    ImageView unlock ;
    Switch s;
    TextView textview;
    String chporte;
    DatabaseReference mDatabase;
    boolean active = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_porte, container, false);
        s  =   (Switch) rootView.findViewById(R.id.switch1);
        unlock=(ImageView)  rootView.findViewById(R.id.imgLogo);

        lock=(ImageView )rootView.findViewById(R.id.imgLogo1);
        textview =(TextView) rootView.findViewById(R.id.textView3);
        mDatabase = FirebaseDatabase.getInstance().getReference();
         String chporte;
        unlock.setVisibility(View.GONE);
        lock.setVisibility(View.VISIBLE);
        boolean b = ((Switch)s).isChecked();

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                    lock.setVisibility(View.GONE);
                    unlock.setVisibility(View.VISIBLE);
                    mDatabase.child("Porte").setValue("Ouvert");
                    textview.setText(" Porte Ouvert");

                }

            else{
                    unlock.setVisibility(View.GONE);
                    lock.setVisibility(View.VISIBLE);
                    mDatabase.child("Porte").setValue("Fermer");
                    textview.setText(" Porte fermé");


                        }

        }
});
       return rootView;
   }}







