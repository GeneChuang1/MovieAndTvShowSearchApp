package com.sample.genechuang.movieandtvsearch.UserInputPages;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.ListViewPages.Act_ListView;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.SavedUserSearch;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.Tags;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Frag_InputMoviesInfo extends Fragment implements View.OnClickListener {
    private View view;
    private EditText etMovieName, etMovieYear;
    private String movieName, movieYear;
    Button bSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_input_page_movie, container, false);
        initializeList();
        return view;
    }

    public void initializeList(){
        etMovieName = (EditText) view.findViewById(R.id.et_movie_name);

        //Code for when the user pressed the enter button on the keyboard, it hits
        //the search button and brings the user to the ListView page.
        //Frag_InputMoviesInfo.java does the same thing but in another way.
        etMovieName.setImeOptions(EditorInfo.IME_ACTION_DONE); //Value of 6.
        etMovieName.setSingleLine();
        //setImeOptions and setSingleLine is so that setOnEditorActionListener only gets called 1x.
        //instead of 2x.
        etMovieName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView tv, int actionId, KeyEvent event) {
                //System.out.println("actionId= "+ actionId); //Usually this is 0 unless I do setImeOptions() and setSingleLine().
                return performSearch();
            }
        });

        etMovieYear = (EditText) view.findViewById(R.id.et_movie_year);

        bSearch= (Button) view.findViewById(R.id.search_button_movie);
        bSearch.setOnClickListener(this);

        SavedUserSearch.getInstance().setUserBookmark(Tags.ENUM_MOVIE_OR_TV.MOVIE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button_movie:
                performSearch();
                break;
        }
    }

    private boolean performSearch(){
        movieName = etMovieName.getText().toString();
        movieYear = etMovieYear.getText().toString();

        Formatting formatting= new Formatting(movieName, movieYear, getActivity());
        if (formatting.checkYearFormatting()==false){
            return false;
        } else if (formatting.checkNameFormatting()==false){
            return false;
        } else {
            movieName = formatting.getFormattedName();
            Intent stuffToSend = new Intent( getActivity().getApplicationContext(), Act_ListView.class);
            SavedUserSearch.getInstance().setMovieName(movieName);
            SavedUserSearch.getInstance().setMovieYear(movieYear);
            this.startActivity(stuffToSend);
            return true;
        }
    }
}
