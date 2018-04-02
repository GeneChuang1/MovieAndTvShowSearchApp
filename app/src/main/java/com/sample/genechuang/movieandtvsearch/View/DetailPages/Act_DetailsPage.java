package com.sample.genechuang.movieandtvsearch.View.DetailPages;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sample.genechuang.movieandtvsearch.Controller.GetMovieDetails;
import com.sample.genechuang.movieandtvsearch.Model.Movie;
import com.sample.genechuang.movieandtvsearch.View.ListViewPages.Act_ListView;
import com.sample.genechuang.movieandtvsearch.View.ListViewPages.ImageUtilities.AppController;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.Controller.Utilities.ServiceHandler;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;

import org.json.JSONException;
import org.json.JSONObject;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Act_DetailsPage extends Activity {
    private static final String Tracking = "Tracking";
    private String imdbID = "";
    private ProgressDialog pDialog;

    Activity activity= this;
    SharedPreferences.Editor itemsViewedBefore;
    SharedPreferences shared_prefs;

    private String baseURL = "http://www.omdbapi.com/?i=";
    private String finalURL = "";

    TextView tvTitle, tvYear, tvRated, tvReleased, tvRuntime, tvGenre, tvDirector,
            tvWriter, tvActors, tvPlot, tvLanguage, tvCountry, tvAwards, tvMetascore,
            tvImdbRating, tvImdbVotes, tvImdbID, tvType, tvResponse, tvViewed;

    String sTitle, sYear, sRated, sReleased, sRuntime, sGenre, sDirector,
            sWriter, sActors, sPlot, sLanguage, sCountry, sAwards,sPoster, sMetascore,
            sImdbRating, sImdbVotes, sImdbID, sType, sResponse, sViewed;
    Button bBackButton;
    NetworkImageView poster;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(Tracking, "Inside onCreate() of Act_DetailsPage.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        initializeVariables();

        // Calling async task to get json
        new GetAllMovieDetails().execute();  //Original Code

//-----------------Start of Gene's Test Code--------------------------------
//        GetMovieDetails getMovieDetails= new GetMovieDetails();
//        Movie movie= getMovieDetails.makeHttpCall(this, finalURL);
//
//        setDisplay(movie);
//-----------------Start of Gene's Test Code--------------------------------
    }

    private void initializeVariables() {
        //Creates a Editor to PUT stuff into Shared Preferences.
        itemsViewedBefore = getApplicationContext().getSharedPreferences(Tags.SHARED_PREF, MODE_PRIVATE).edit();

        //Creates an object that can RETRIEVE shared preferences' data.
        shared_prefs= getSharedPreferences(Tags.SHARED_PREF, MODE_PRIVATE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            imdbID = extras.getString(JSON_Tags.TAG_ID);
            Log.i(Tracking, "Act_DetailsPage. imdbID= " + imdbID);
        }

        finalURL = baseURL + imdbID + "&plot=full&r=json"+ ServiceHandler.apiKey;
        Log.i(Tracking, "Act_DetailsPage. finalURL= " + finalURL);

        poster = (NetworkImageView) findViewById(R.id.detail_thumbnail);
        tvTitle = (TextView) findViewById(R.id.detail_title);
        tvYear = (TextView) findViewById(R.id.detail_year);
        tvRated = (TextView) findViewById(R.id.detail_rated);
        tvReleased = (TextView) findViewById(R.id.detail_released);
        tvRuntime = (TextView) findViewById(R.id.detail_runtime);
        tvGenre = (TextView) findViewById(R.id.detail_genre);
        tvDirector = (TextView) findViewById(R.id.detail_director);
        tvWriter = (TextView) findViewById(R.id.detail_writer);
        tvActors = (TextView) findViewById(R.id.details_actors);
        tvPlot = (TextView) findViewById(R.id.details_plot);
        tvLanguage = (TextView) findViewById(R.id.detail_language);
        tvCountry = (TextView) findViewById(R.id.detail_country);
        tvAwards = (TextView) findViewById(R.id.detail_awards);
        tvMetascore = (TextView) findViewById(R.id.detail_metascore);
        tvImdbRating = (TextView) findViewById(R.id.detail_imdb_rating);
        tvImdbVotes = (TextView) findViewById(R.id.detail_imdb_votes);
        tvImdbID = (TextView) findViewById(R.id.detail_imdb_id);
        tvType = (TextView) findViewById(R.id.detail_type);
        tvResponse = (TextView) findViewById(R.id.detail_response);
        tvViewed = (TextView) findViewById(R.id.detail_viewed);
        bBackButton= (Button) findViewById(R.id.detail_back_button);
        bBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stuffToSend = new Intent(getApplicationContext(), Act_ListView.class);
                activity.finish();
                startActivity(stuffToSend);
            }
        });


        // Code to save movie/tv show as viewed before inside SharedPreferences
        itemsViewedBefore.putString(imdbID, Tags.SHARED_PREF_VIEWED);
        itemsViewedBefore.commit();
    }

//    private void setDisplay(Movie m){
//        Log.i(Tracking, "Inside setDisplay() of Act_DetailsPage.java");
//        //thumbnail image
//        poster.setImageUrl(m.getPosterURL(), imageLoader);
//        Log.i(Tracking, "m.getTitle()= "+ m.getTitle());
//        tvTitle.setText(m.getTitle());
//        tvYear.setText(m.getYear());
//        tvRated.setText(m.getRated());
//        tvReleased.setText(m.getReleased());
//        tvRuntime.setText(m.getRuntime());
//        tvGenre.setText(m.getGenre());
//        tvDirector.setText(m.getDirector());
//        tvWriter.setText(m.getWriter());
//        tvActors.setText(m.getActors());
//        tvPlot.setText(m.getPlot());
//        tvLanguage.setText(m.getLanguage());
//        tvCountry.setText(m.getCountry());
//        tvAwards.setText(m.getAwards());
//        tvMetascore.setText(m.getMetascore());
//        tvImdbRating.setText(m.getImdbRating());
//        tvImdbVotes.setText(m.getImdbVotes());
//        tvImdbID.setText(m.getImdbID());
//        tvType.setText(m.getType());
//        tvResponse.setText(m.getResponse());
//        String beenViewed= shared_prefs.getString(imdbID, Tags.SHARED_PREF_NOT_VIEWED);
//        tvViewed.setText(beenViewed);
//    }

    private void setDisplay() {
        // thumbnail image
        poster.setImageUrl(sPoster, imageLoader);

        tvTitle.setText(sTitle);
        tvYear.setText(sYear);
        tvRated.setText(sRated);
        tvReleased.setText(sReleased);
        tvRuntime.setText(sRuntime);
        tvGenre.setText(sGenre);
        tvDirector.setText(sDirector);
        tvWriter.setText(sWriter);
        tvActors.setText(sActors);
        tvPlot.setText(sPlot);
        tvLanguage.setText(sLanguage);
        tvCountry.setText(sCountry);
        tvAwards.setText(sAwards);
        tvMetascore.setText(sMetascore);
        tvImdbRating.setText(sImdbRating);
        tvImdbVotes.setText(sImdbVotes);
        tvImdbID.setText(sImdbID);
        tvType.setText(sType);
        tvResponse.setText(sResponse);
        tvViewed.setText(sViewed);
    }


    private class GetAllMovieDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(activity);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(finalURL, ServiceHandler.GET);
            System.out.println("Act_DetailsPage. jsonStr= " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject everything = new JSONObject(jsonStr);

                    sTitle = everything.getString(JSON_Tags.TAG_TITLE);
                    sYear = everything.getString(JSON_Tags.TAG_YEAR);
                    sRated = everything.getString(JSON_Tags.TAG_RATED);
                    sReleased = everything.getString(JSON_Tags.TAG_RELEASED);
                    sRuntime = everything.getString(JSON_Tags.TAG_RUNTIME);
                    sGenre = everything.getString(JSON_Tags.TAG_GENRE);
                    sDirector = everything.getString(JSON_Tags.TAG_DIRECTOR);
                    sWriter = everything.getString(JSON_Tags.TAG_WRITER);
                    sActors = everything.getString(JSON_Tags.TAG_ACTORS);
                    sPlot = everything.getString(JSON_Tags.TAG_PLOT);
                    sLanguage = everything.getString(JSON_Tags.TAG_LANGUAGE);
                    sCountry = everything.getString(JSON_Tags.TAG_COUNTRY);
                    sAwards = everything.getString(JSON_Tags.TAG_AWARDS);
                    sPoster = everything.getString(JSON_Tags.TAG_POSTER);
                    sMetascore = everything.getString(JSON_Tags.TAG_METASCORE);
                    sImdbRating = everything.getString(JSON_Tags.TAG_IMDB_RATING);
                    sImdbVotes = everything.getString(JSON_Tags.TAG_IMDB_VOTES);
                    sImdbID = everything.getString(JSON_Tags.TAG_IMDB_ID);
                    sType = everything.getString(JSON_Tags.TAG_TYPE);
                    sResponse = everything.getString(JSON_Tags.TAG_RESPONSE);

                    sViewed= shared_prefs.getString(imdbID, Tags.SHARED_PREF_NOT_VIEWED);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            setDisplay();
        }
    }
}
