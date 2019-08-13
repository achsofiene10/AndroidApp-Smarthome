package com.example.dell.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgress;
    private Button btn ;
    private Button send;
    private Button cancel;
    private EditText email;
    private EditText password;
    private TextView textView;
    private FirebaseAuth mAuth;
    private EditText resetmail;
    private static final String TAG = "EmailPassword";
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
    public void openDialog( ) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alertdialo);
        send=(Button) dialog.findViewById(R.id.dialog_ok);
        cancel=(Button) dialog.findViewById(R.id.dialog_cancel);
        resetmail=(EditText) dialog.findViewById(R.id.edit);
        dialog.setTitle("Récuperation du Compte");
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String mailreset=resetmail.getText().toString();
                if (mailreset.length()==0){Toast.makeText(MainActivity.this, "écrire votre email",
                        Toast.LENGTH_SHORT).show();}else{
                mProgress.show();
                FirebaseAuth.getInstance().sendPasswordResetEmail(mailreset)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mProgress.dismiss();
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, "Code envoyé avec succés",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Email sent.");
                                } else {
                                    mProgress.dismiss();
                                    Toast.makeText(MainActivity.this, "Réessayer  ",
                                        Toast.LENGTH_SHORT).show();
                                    resetmail.setText("");}
                            }
                        });
            }}
        });






    }}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );

        btn = findViewById(R.id.btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        textView = findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDialog( );
                }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                String ch = email.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(ch) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(MainActivity.this, " Remplir les champs ", Toast.LENGTH_SHORT).show();
                } else {  mProgress.show();mAuth.signInWithEmailAndPassword(ch, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "signUserWithEmail:success");
                                    mProgress.dismiss();
                                    startActivity(new Intent(MainActivity.this,acceuil.class));
                                    MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.opening);
                                    ring.start();
                                    email.setText("");
                                    password.setText("");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    mProgress.dismiss();
                                    Log.w(TAG, "signUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    password.setText("");
                                    email.setText("");

                                }


                            }
                        });

                }
            }}
        });


    }}












