package com.sample.genechuang.movieandtvsearch.ListViewPages;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.SavedUserSearch;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.Tags;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Act_ListView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (SavedUserSearch.getInstance().getUserBookmark()== Tags.ENUM_MOVIE_OR_TV.MOVIE){
            //User in Movie's Tab
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragMoviesList= new Frag_MoviesList();
            transaction.replace(R.id.fragment_place, fragMoviesList);
            transaction.commit();
        } else if (SavedUserSearch.getInstance().getUserBookmark()== Tags.ENUM_MOVIE_OR_TV.TV) {
            //User in TV Shows Tab
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragTvShowList = new Frag_TvShowsList();
            transaction.replace(R.id.fragment_place, fragTvShowList);
            transaction.commit();
        }
    }
}
