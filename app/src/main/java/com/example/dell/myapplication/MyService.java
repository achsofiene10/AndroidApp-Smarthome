package com.example.dell.myapplication;

import android.app.Service;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.*;
import android.widget.Toast;

public class MyService extends Service {

    public Context context = this;

    public boolean isNetworkAvailable(Context con) {
        try {
            ConnectivityManager cm = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

    }
    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
            Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();
            if (isNetworkAvailable(getApplicationContext()))
            {
                Toast.makeText(this, "Connection Ok ", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Connection Not Ok ", Toast.LENGTH_LONG).show();
            }
        }
    }


