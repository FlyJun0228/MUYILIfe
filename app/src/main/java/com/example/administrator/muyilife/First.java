package com.example.administrator.muyilife;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class First extends Fragment {
public View onCreateView(LayoutInflater inflater, ViewGroup cotainer, Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.activity_first,cotainer,false);
    return view;
    }
}