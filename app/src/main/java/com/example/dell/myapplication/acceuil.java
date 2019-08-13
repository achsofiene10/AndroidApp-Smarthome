package com.example.dell.myapplication;

import android.app.ProgressDialog;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;




import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class acceuil extends AppCompatActivity
                implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference mDatabase;
    DatabaseReference mDatabase1;
    DatabaseReference mDatabase2;
    DatabaseReference mDatabase3;
    DatabaseReference mDatabase4;
    DatabaseReference mDatabase5;

    ProgressDialog mProgress;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_acceuil);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                Intent intent = new Intent(this, Listacceuil.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
               final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.gaz)
                        .setContentTitle("Alert ! ")
                        .setContentText("Fuite de gaz detecté")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                final NotificationCompat.Builder mBuilder1 = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.mouv)
                        .setContentTitle("Alert ! ")
                        .setContentText("Mouvement detecté")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                final NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.temperature1)
                        .setContentTitle("Alert ! ")
                        .setContentText("Temperature elevée")
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                BlankFragment lmp=new BlankFragment();
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.Fragment,lmp);
                ft.commit();
                mDatabase= FirebaseDatabase.getInstance().getReference().child("gaz");
                mDatabase1=FirebaseDatabase.getInstance().getReference().child("mouvement");
                mDatabase2=FirebaseDatabase.getInstance().getReference().child("temperature");
                mDatabase4=FirebaseDatabase.getInstance().getReference().child("notifs");
                mDatabase5=FirebaseDatabase.getInstance().getReference().child("sons");
                mDatabase4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue().toString().equals("activé")){

                            mDatabase.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue().toString().equals("1")){
                                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                                        notificationManager.notify(1, mBuilder.build());
                                        mDatabase5.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getValue().toString().equals("activé")){
                                                MediaPlayer ring= MediaPlayer.create(acceuil.this,R.raw.notif);
                                                ring.start();}
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                            mDatabase3=FirebaseDatabase.getInstance().getReference().child("systeme");
                            mDatabase1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue().toString().equals("oui")){
                                        mDatabase3.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.getValue().toString().equals("desactive")){
                                                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                                                    notificationManager.notify(1, mBuilder1.build());
                                                    mDatabase5.addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                            if(dataSnapshot.getValue().toString().equals("activé")){
                                                                MediaPlayer ring= MediaPlayer.create(acceuil.this,R.raw.notif);
                                                                ring.start();}
                                                        }

                                                        @Override
                                                        public void onCancelled(DatabaseError databaseError) {

                                                        }
                                                    });
                                                }
                                            }
                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                            mDatabase2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (Integer.valueOf(dataSnapshot.getValue().toString())>39){
                                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                                        notificationManager.notify(1, mBuilder2.build());
                                        mDatabase5.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.getValue().toString().equals("activé")){
                                                    MediaPlayer ring= MediaPlayer.create(acceuil.this,R.raw.notif);
                                                    ring.start();}
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                mProgress = new ProgressDialog(this);
                mProgress.setTitle("Processing...");
                mProgress.setMessage("Please wait...");
                mProgress.setCancelable(false);
                mProgress.setIndeterminate(true);


                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.addDrawerListener(toggle);
                toggle.syncState();

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);
            }


            @Override
            public void onBackPressed() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    super.onBackPressed();
                }
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.acceuil, menu);

                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    startActivity(new Intent(acceuil.this,MainActivity.class));
                }

                return super.onOptionsItemSelected(item);
            }


            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment;

                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    startActivity(new Intent(acceuil.this,Listacceuil.class));
                } else if (id == R.id.nav_gallery) {
                    Listfragment cap=new Listfragment();
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.Fragment,cap);
                    ft.commit();

                } else if (id == R.id.nav_slideshow) {
                    Notifications n=new Notifications();
                    FragmentManager fm=getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    ft.replace(R.id.Fragment,n);
                    ft.commit();

                } else if (id == R.id.nav_send) {
                    mProgress.show();
                    FirebaseAuth.getInstance().signOut();
                    mProgress.dismiss();
                    startActivity(new Intent(acceuil.this,MainActivity.class));
                }
                final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                return true;
    }

}
