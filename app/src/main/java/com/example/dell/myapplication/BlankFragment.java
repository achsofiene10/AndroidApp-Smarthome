package com.example.dell.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
TextView user;
    TextView etat;
CircleImageView led;
    FirebaseAuth  mAuth;
    public BlankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.profile, container, false);
        user=rootview.findViewById(R.id.textView23);
        etat=rootview.findViewById(R.id.textView24);
        led=rootview.findViewById(R.id.led);
        mAuth = FirebaseAuth.getInstance();
        if (!isNetworkAvailablee(getContext())) {
            led.setImageResource(R.drawable.deconecte);
            etat.setText("Déconnecté");
            user.setText("Nom dutilisateur");
        }
        else {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser == null) {
                led.setImageResource(R.drawable.deconecte);
                etat.setText("Déconnecté");
                user.setText("Connecter vous");

            } else {
                user.setText(currentUser.getEmail());
                led.setImageResource(R.drawable.conecte);
                etat.setText("Connecté");

            }
        }
        return rootview;
    }

}
