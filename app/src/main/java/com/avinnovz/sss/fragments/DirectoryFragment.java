package com.avinnovz.sss.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avinnovz.sss.R;
import com.avinnovz.sss.activities.DirectoryDetailActivity;
import com.avinnovz.sss.activities.ManagementActivity;
import com.avinnovz.sss.activities.UsefulInfoActivity;
import com.avinnovz.sss.adapters.DirectoryAdapter;
import com.avinnovz.sss.adapters.FaqsAdapter;
import com.avinnovz.sss.interfaces.OnListItemClickListener;
import com.avinnovz.sss.models.Directory;
import com.avinnovz.sss.models.Faq;
import com.avinnovz.sss.models.events.DirectoryItemClickEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DirectoryFragment extends Fragment {

    @BindView(R.id.rvDirectory)
    RecyclerView rvDirectory;
    private ManagementActivity activity;
    private ArrayList<Directory> directories;

    public static DirectoryFragment newInstance(ArrayList<Directory> directories) {
        DirectoryFragment fragment = new DirectoryFragment();
        fragment.directories = directories;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (ManagementActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_directory, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    private void initRecycler() {
        DirectoryAdapter adapter = new DirectoryAdapter(activity, directories, new OnListItemClickListener() {
            @Override
            public void onItemClick(Object obj) {
                EventBus.getDefault().postSticky(new DirectoryItemClickEvent((Directory) obj));
                activity.moveToOtherActivity(DirectoryDetailActivity.class);
            }
        });
        rvDirectory.setAdapter(adapter);
        rvDirectory.setLayoutManager(new LinearLayoutManager(activity));
    }
}
