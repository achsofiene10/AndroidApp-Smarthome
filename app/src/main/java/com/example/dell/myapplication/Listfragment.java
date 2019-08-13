package com.example.dell.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

public class Listfragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View rootview =inflater.inflate(R.layout.fragment_capteurs, container, false);
        RecyclerView recyclerView=(RecyclerView) rootview.findViewById(R.id.capteurs);
        ListAdapter listadapter=new ListAdapter();
        recyclerView.setAdapter(listadapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return rootview;
    }
    }



