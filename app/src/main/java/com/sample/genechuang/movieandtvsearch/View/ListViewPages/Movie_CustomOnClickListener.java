package com.sample.genechuang.movieandtvsearch.View.ListViewPages;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.View.DetailPages.Act_DetailsPage;
import com.sample.genechuang.movieandtvsearch.Model.Movie;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Movie_CustomOnClickListener implements View.OnClickListener {
    private static final String Tracking = "Tracking";

    int mPositon;
    Movie mMovie;
    Activity mActivity;
    TextView mViewedBefore; //This is so when the user pushes the back button, it shows Viewed on the ListView.

    public Movie_CustomOnClickListener(int position, Movie movie, Activity activity, TextView viewedBefore){
        super();
        mPositon= position;
        mMovie= movie;
        mActivity= activity;
        mViewedBefore= viewedBefore;
    }

    @Override
    public void onClick(View v) {
        Log.i(Tracking, "Inside onClick() of Movie_CustomOnClickListener.java");
        String imdbID = mMovie.getImdbID();

        mViewedBefore.setText(Tags.SHARED_PREF_VIEWED);

        Intent newPage = new Intent(mActivity.getApplicationContext(), Act_DetailsPage.class);
        newPage.putExtra(JSON_Tags.TAG_ID, imdbID);
        mActivity.finish();
        mActivity.startActivity(newPage);
    }
}
