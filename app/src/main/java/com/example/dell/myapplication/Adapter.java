package com.example.dell.myapplication;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Adapter {
   

    public static String[] title=new String[]{
            "système",
             "Température",
            "humidite",
            "Gaz",
            "Mouvement",
            "ventilo",
            "Porte"

    };

        public static int[] picture=new int[]{
            R.drawable.securite,
            R.drawable.temperature1,
            R.drawable.humidite1,
            R.drawable.gaz,
            R.drawable.mouv,
            R.drawable.ventil1,
            R.drawable.portes

    };




    };
