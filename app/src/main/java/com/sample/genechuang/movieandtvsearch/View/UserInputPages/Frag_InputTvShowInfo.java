package com.sample.genechuang.movieandtvsearch.View.UserInputPages;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.Controller.Formatting;
import com.sample.genechuang.movieandtvsearch.View.ListViewPages.Act_ListView;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.SavedUserSearch;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Frag_InputTvShowInfo extends Fragment implements View.OnClickListener {
    private static final String Tracking = "Tracking";
    private View view;
    private EditText etTvShowName, etTvShowYear;
    private String tvShowName, tvShowYear;
    Button bSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_input_page_shows, container, false);
        initializeList();
        return view;
    }

    public void initializeList(){
        etTvShowName = (EditText) view.findViewById(R.id.et_show_name);
        etTvShowName.setSingleLine();
        
        //Code for when the user pressed the enter button on the keypad, it hits
        //the search button and brings the user to the ListView page.
        //Frag_InputMoviesInfo.java does the same thing but in another way.
        etTvShowName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //System.out.println("actionId= "+ actionId);
                return performSearch();
                //return false; //If I return true, setOnEditorActionListener gets called 2x.
            }
        });

        etTvShowYear = (EditText) view.findViewById(R.id.et_show_year);

        bSearch= (Button) view.findViewById(R.id.search_button_show);
        bSearch.setOnClickListener(this);

        SavedUserSearch.getInstance().setUserBookmark(Tags.ENUM_MOVIE_OR_TV.TV);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button_show:
                performSearch();
                break;
        }
    }

    private boolean performSearch(){
        tvShowName = etTvShowName.getText().toString();
        tvShowYear = etTvShowYear.getText().toString();

        Formatting formatting= new Formatting(tvShowName, tvShowYear, getActivity());
        if (formatting.checkYearFormatting()==false){
            return false;
        } else if (formatting.checkNameFormatting()==false){
            return false;
        } else {
            tvShowName = formatting.getFormattedName();
            Intent stuffToSend = new Intent( getActivity().getApplicationContext(), Act_ListView.class);
            SavedUserSearch.getInstance().setTvShowsName(tvShowName);
            SavedUserSearch.getInstance().setTvShowsYear(tvShowYear);
            this.startActivity(stuffToSend);
            return true;
        }
    }
}
