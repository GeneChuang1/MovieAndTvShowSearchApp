package com.sample.genechuang.movieandtvsearch.UserInputPages;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.sample.genechuang.movieandtvsearch.R;

//import android.support.v4.app.Fragment;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class MyTabListener implements ActionBar.TabListener {

    private Fragment fragment;

    public MyTabListener(Fragment fragment)
    {
        this.fragment = fragment;
    }

    public void onTabSelected(Tab tab, FragmentTransaction ft)
    {
        ft.add(R.id.fragment_place, fragment, null);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft)
    {
        ft.remove(fragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft)
    {}
}