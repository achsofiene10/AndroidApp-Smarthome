package com.example.dell.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.dell.myapplication.isNetworkAvailable.isNetworkAvailablee;

public class Listacceuil extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList arrayList;
    Button auto;
    Button man;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridtest);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        auto=findViewById(R.id.button2);
        man=findViewById(R.id.button3);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("mode");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("auto")){
                    auto.setTextColor(Color.GREEN);
                    man.setTextColor(Color.WHITE);
                }
                else{man.setTextColor(Color.GREEN);
                    auto.setTextColor(Color.WHITE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                    mDatabase.setValue("auto");
                    auto.setTextColor(Color.GREEN);
                    man.setTextColor(Color.WHITE);
                }
            }
        });
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isNetworkAvailablee(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "No connection ", Toast.LENGTH_LONG).show();
                }else {
                mDatabase.setValue("manuel");
                man.setTextColor(Color.GREEN);
                auto.setTextColor(Color.WHITE);}
            }
        });


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position==1){
                    Intent intent= new Intent(view.getContext(),activity_porte.class);
                  overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);

                    startActivityForResult(intent,2);}
                if (position==5){
                    Intent intent= new Intent(view.getContext(),Activity_ventil.class);
                    startActivityForResult(intent,5);}
                if (position== 2){
                Intent intent= new Intent(view.getContext(),Listlampe.class);
                startActivityForResult(intent,3);}
                if (position==0){ Intent intent= new Intent(view.getContext(),Securite.class);
                    startActivityForResult(intent,4);}
                if (position==4){
                    Intent intent= new Intent(view.getContext(),Camera.class);
                    startActivityForResult(intent,4);}
                if (position==3){
                    Intent intent= new Intent(view.getContext(),Main2Activity.class);
                    startActivityForResult(intent,3);}
            }
        }));

        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);}
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
            startActivity(new Intent(Listacceuil.this,MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
    }


