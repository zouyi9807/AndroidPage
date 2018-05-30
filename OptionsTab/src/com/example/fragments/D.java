package com.example.fragments;

import com.example.optiontab.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class D extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("DDDDDDDDDDDDDD____onCreateView");
        return inflater.inflate(R.layout.d, container, false);
    }
}
