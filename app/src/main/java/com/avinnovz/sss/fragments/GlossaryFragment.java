package com.avinnovz.sss.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avinnovz.sss.R;

/**
 * Created by jayan on 8/29/2016.
 */
public class GlossaryFragment extends Fragment {
    public static GlossaryFragment newInstance() {
        GlossaryFragment fragment = new GlossaryFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_glossary, container, false);
        return view;
    }
}
