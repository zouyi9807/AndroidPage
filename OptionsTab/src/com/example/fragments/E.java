package com.example.fragments;

import com.example.optiontab.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class E extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("EEEEEEEEEEEE____onCreateView");
        return inflater.inflate(R.layout.e, container, false);
    }
}
