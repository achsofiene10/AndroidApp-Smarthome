package com.example.dell.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Keypad extends AppCompatActivity {
Button save ;
Button cancel;
EditText mdp;
EditText mdp1;
EditText mdp2;
String x;
DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keypad);
        setTitle("Changer mot de passe Keypad");
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        save=findViewById(R.id.button6);
        cancel=findViewById(R.id.button7);
        mdp=findViewById(R.id.editText);
        mdp1=findViewById(R.id.editText2);
        mdp2=findViewById(R.id.editText3);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("keypad");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                x=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                } else {
                String ancien=mdp.getText().toString();
                String n1=mdp1.getText().toString();
                String n2=mdp2.getText().toString();
                if ((TextUtils.isEmpty(ancien)) || (TextUtils.isEmpty(n1)) || (TextUtils.isEmpty(n2)))
                {
                    Toast.makeText(getApplicationContext(), "Remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else{
                    if(!ancien.equals(x)) {
                        Toast.makeText(getApplicationContext(), "Mot de passe actuel incorrect", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if((!isNumeric(n1)) || (!isNumeric(n2))){
                            Toast.makeText(getApplicationContext(), "Ils doivent être Numeriques", Toast.LENGTH_SHORT).show();
                        }
                        else if (!n1.equals(n2)) {
                            Toast.makeText(getApplicationContext(), "Ils doivent être identiques", Toast.LENGTH_SHORT).show();
                        }
                         else{
                            mDatabase.setValue(n1);

                          }}
            }}}
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Keypad.this, Securite.class));
            }
        });

}
public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }}
