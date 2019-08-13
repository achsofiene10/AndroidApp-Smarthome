package com.example.dell.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Camera extends AppCompatActivity {
ImageView droit;
ImageView gauche;
DatabaseReference mDatabase;
int x;
int y ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Camera");
        setContentView(R.layout.activity_camera);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        VideoView videoView = (VideoView) findViewById(R.id.Video);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        Uri video = Uri.parse("{VIDEO_RTSP_URL}");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
        if (!isNetworkAvailablee(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
        }
        droit=findViewById(R.id.imgLogo1);
        gauche=findViewById(R.id.imgLogo);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("servo");

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        x=Integer.parseInt(dataSnapshot.getValue().toString());
                        droit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (x<700){
                                    x=x+175;
                                    mDatabase.setValue(String.valueOf(x));
                                }
                            }
                        });

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        y=Integer.parseInt(dataSnapshot.getValue().toString());
                        gauche.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (y>0){
                                    y=y-175;
                                    mDatabase.setValue(String.valueOf(y));
                                }
                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }}