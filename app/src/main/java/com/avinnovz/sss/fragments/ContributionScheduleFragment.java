package com.avinnovz.sss.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avinnovz.sss.R;

public class ContributionScheduleFragment extends Fragment {

    public static ContributionScheduleFragment newInstance() {
        ContributionScheduleFragment fragment = new ContributionScheduleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contribution_schedule, container, false);
    }

}
