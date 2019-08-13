package com.example.dell.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Notifications extends Fragment {
    private List<Notif> notifList = new ArrayList<>();
    private RecyclerView recyclerView;
    private notif_adapter mAdapter;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_notifications, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.notifs);
        mAdapter = new notif_adapter(notifList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        final int[] picture=new int[]{

                R.drawable.portes,
                R.drawable.ventil1,
                R.drawable.gaz,
                R.drawable.mouv,
                R.drawable.temperature1,
                R.drawable.ventil,
                R.drawable.humidite,

        };
        if (!isNetworkAvailablee(getContext())) {
            Toast.makeText(getContext(), "No connection ", Toast.LENGTH_LONG).show();
        }else {
            mDatabase = FirebaseDatabase.getInstance().getReference().child("events");
            mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.hasChild("action")) {
                        String actionn = String.valueOf(dataSnapshot.child("action").getValue());
                        String idd = String.valueOf(dataSnapshot.child("id").getValue());
                        String heure = String.valueOf(dataSnapshot.child("heure").getValue());
                        prepareNotifData(actionn, picture[Integer.parseInt(idd)], heure);
                        mAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        return rootView;
    }
         private void prepareNotifData(String text, int pic,String time) {
                 Notif not = new Notif(text,pic,time);
             notifList.add(0,not);}

    }

