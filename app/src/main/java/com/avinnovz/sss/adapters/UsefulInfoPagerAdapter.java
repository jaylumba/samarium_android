package com.avinnovz.sss.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by jayan on 8/29/2016.
 */
public class UsefulInfoPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] {"Schedule of Contributions","FAQs","Glossary of Terms"};
    private ArrayList<Fragment> fragments;

    public UsefulInfoPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
