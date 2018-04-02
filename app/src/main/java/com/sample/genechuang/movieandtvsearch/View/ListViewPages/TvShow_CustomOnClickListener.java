package com.sample.genechuang.movieandtvsearch.View.ListViewPages;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.View.DetailPages.Act_DetailsPage;
import com.sample.genechuang.movieandtvsearch.Model.TvShow;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class TvShow_CustomOnClickListener implements View.OnClickListener {
    int mPositon;
    TvShow mShow;
    Activity mActivity;
    TextView mViewedBefore; //This is so when the user pushes the back button, it shows Viewed on the ListView

    public TvShow_CustomOnClickListener(int position, TvShow show, Activity activity, TextView viewedBefore){
        super();
        mPositon= position;
        mShow = show;
        mActivity= activity;
        mViewedBefore= viewedBefore;
    }


    @Override
    public void onClick(View v) {
        String imdbID = mShow.getID();

        mViewedBefore.setText(Tags.SHARED_PREF_VIEWED);

        Intent newPage = new Intent(mActivity.getApplicationContext(), Act_DetailsPage.class);
        newPage.putExtra(JSON_Tags.TAG_ID, imdbID);
        mActivity.finish();
        mActivity.startActivity(newPage);
    }
}
