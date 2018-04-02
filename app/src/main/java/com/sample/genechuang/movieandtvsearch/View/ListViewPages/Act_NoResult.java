package com.sample.genechuang.movieandtvsearch.View.ListViewPages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.SavedUserSearch;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.View.UserInputPages.Act_UserInputPages;

public class Act_NoResult extends Activity implements View.OnClickListener {
    private static final String Tracking = "Tracking";
    Button backButton;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Tracking, "Inside onClick() of Act_NoResult.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_result_page);

        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        message= (TextView) findViewById(R.id.message);
        if (SavedUserSearch.getInstance().getUserBookmark()== Tags.ENUM_MOVIE_OR_TV.MOVIE){
            message.setText("Sorry, couldn't find the movie you requested.");
        } else if (SavedUserSearch.getInstance().getUserBookmark()== Tags.ENUM_MOVIE_OR_TV.TV) {
            message.setText("Sorry, couldn't find the TV Show you requested.");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                Intent newPage = new Intent(this.getApplicationContext(), Act_UserInputPages.class);
                this.finish();
                this.startActivity(newPage);
                break;
        }
    }
}