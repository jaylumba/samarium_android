package com.avinnovz.sss.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avinnovz.sss.R;
import com.avinnovz.sss.activities.UsefulInfoActivity;
import com.avinnovz.sss.adapters.FaqsAdapter;
import com.avinnovz.sss.models.Faq;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaqFragment extends Fragment {

    @BindView(R.id.rvFaqs)
    RecyclerView rvFaqs;
    private UsefulInfoActivity activity;
    private ArrayList<Faq> faqs;

    public static FaqFragment newInstance(ArrayList<Faq> faqs) {
        FaqFragment fragment = new FaqFragment();
        fragment.faqs = faqs;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (UsefulInfoActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        FaqsAdapter adapter = new FaqsAdapter(activity, faqs);
        rvFaqs.setAdapter(adapter);
        rvFaqs.setLayoutManager(new LinearLayoutManager(activity));
    }

}
