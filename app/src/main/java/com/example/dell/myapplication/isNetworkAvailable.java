package com.example.dell.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class isNetworkAvailable{

public static boolean isNetworkAvailablee(Context con) {
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
        }}