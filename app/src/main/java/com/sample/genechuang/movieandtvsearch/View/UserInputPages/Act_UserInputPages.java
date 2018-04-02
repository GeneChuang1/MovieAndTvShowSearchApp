package com.sample.genechuang.movieandtvsearch.View.UserInputPages;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.View.UserInputPages.Frag_InputMoviesInfo;
import com.sample.genechuang.movieandtvsearch.View.UserInputPages.Frag_InputTvShowInfo;
import com.sample.genechuang.movieandtvsearch.View.UserInputPages.MyTabListener;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Act_UserInputPages extends Activity {

    //Works
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ActionBar bar = getActionBar();
        bar.setDisplayShowTitleEnabled(false);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tabA = bar.newTab().setText("Movies");
        ActionBar.Tab tabB = bar.newTab().setText("TV Shows");

        Fragment fragMoviesTab= new Frag_InputMoviesInfo();
        Fragment fragTVTab = new Frag_InputTvShowInfo();

        tabA.setTabListener(new MyTabListener(fragMoviesTab));
        tabB.setTabListener(new MyTabListener(fragTVTab));
        bar.addTab(tabA);
        bar.addTab(tabB);
    }
}
