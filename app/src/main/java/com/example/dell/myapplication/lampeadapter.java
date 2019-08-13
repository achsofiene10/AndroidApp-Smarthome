package com.example.dell.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class lampeadapter extends RecyclerView.Adapter {

public DatabaseReference mDatabase;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listlampe,parent,false);
        ListViewHolder vh = new ListViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
        if (position==0) {
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("lampe1").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if((Integer.parseInt(dataSnapshot.getValue().toString())!=0)) {
                        ((ListViewHolder) holder).seekbar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                        ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);
                    } else{
                        ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                        ((ListViewHolder) holder).seekbar.setProgress(0);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        ((ListViewHolder) holder).On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListViewHolder) holder).seekbar.setProgress(1);
                mDatabase.child("lampe1").setValue("1");
                ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);
                notifyDataSetChanged();
            }
        });
        ((ListViewHolder) holder).Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ListViewHolder) holder).seekbar.setProgress(0);
                mDatabase.child("lampe1").setValue("0");
                ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                notifyDataSetChanged();
            }
        });
            ((ListViewHolder) holder).seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mDatabase.child("lampe1").setValue(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }
        if (position==1) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("lampe2").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if((Integer.parseInt(dataSnapshot.getValue().toString())!=0)) {
                        ((ListViewHolder) holder).seekbar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                        ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);
                    } else{
                        ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                        ((ListViewHolder) holder).seekbar.setProgress(0);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            ((ListViewHolder) holder).On.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(1);
                    mDatabase.child("lampe2").setValue("1");
                    ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);

                    notifyDataSetChanged();
                }
            });
            ((ListViewHolder) holder).Off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(0);
                    mDatabase.child("lampe2").setValue("0");
                    ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);

                    notifyDataSetChanged();
                }
            });((ListViewHolder) holder).seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mDatabase.child("lampe2").setValue(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        if (position==2) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("lampe3").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if((Integer.parseInt(dataSnapshot.getValue().toString())!=0)) {
                        ((ListViewHolder) holder).seekbar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                        ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);
                    } else{
                        ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                        ((ListViewHolder) holder).seekbar.setProgress(0);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            ((ListViewHolder) holder).On.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(1);
                    mDatabase.child("lampe3").setValue("1");
                    ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);

                    notifyDataSetChanged();
                }
            });
            ((ListViewHolder) holder).Off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(0);
                    mDatabase.child("lampe3").setValue("0");
                    ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);

                    notifyDataSetChanged();
                }
            });((ListViewHolder) holder).seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mDatabase.child("lampe3").setValue(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
        if (position==3) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("lampe4").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if((Integer.parseInt(dataSnapshot.getValue().toString()) !=0)) {
                        ((ListViewHolder) holder).seekbar.setProgress(Integer.parseInt(dataSnapshot.getValue().toString()));
                        ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);
                    } else{
                        ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                        ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                        ((ListViewHolder) holder).seekbar.setProgress(0);

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            ((ListViewHolder) holder).On.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(1);
                    mDatabase.child("lampe4").setValue("1");
                    ((ListViewHolder) holder).On.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).Off.setBackgroundResource(android.R.drawable.btn_default);

                    notifyDataSetChanged();
                }
            });
            ((ListViewHolder) holder).Off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListViewHolder) holder).seekbar.setProgress(0);
                    mDatabase.child("lampe4").setValue("0");
                    ((ListViewHolder) holder).Off.setBackgroundColor(Color.GREEN);
                    ((ListViewHolder) holder).On.setBackgroundResource(android.R.drawable.btn_default);
                    notifyDataSetChanged();
                }
            });
            ((ListViewHolder) holder).seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    mDatabase.child("lampe4").setValue(String.valueOf(i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }

    }
    @Override
    public int getItemCount() {
        return Adapter_lampe.title.length;

    }

    private class  ListViewHolder extends RecyclerView.ViewHolder {

        public TextView nom;
        public ImageView image;
        public SeekBar seekbar ;
        public Button On ;
        public Button Off ;
        public void onClick(View view) {

        }

        public ListViewHolder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.textViewTitle1);
            image = (ImageView) itemView.findViewById(R.id.imageView1);
            seekbar=(SeekBar) itemView.findViewById(R.id.seekBar);
            On=(Button) itemView.findViewById(R.id.button4);
            Off=(Button) itemView.findViewById(R.id.button5);

        }
        public void bindView(int position){
            nom.setText(Adapter_lampe.title[position]);
            image.setImageResource(Adapter_lampe.picture[position]);}

    }}



