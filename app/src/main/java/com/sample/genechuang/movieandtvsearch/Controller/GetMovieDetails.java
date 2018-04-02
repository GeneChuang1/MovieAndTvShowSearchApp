package com.sample.genechuang.movieandtvsearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.sample.genechuang.movieandtvsearch.Controller.Utilities.ServiceHandler;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;
import com.sample.genechuang.movieandtvsearch.Model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

//Discard this class. Not being used. Deprecated!
public class GetMovieDetails{
    private static final String Tracking = "Tracking";
    private ProgressDialog pDialog;
    Activity callingActivity;
    String finalURL;
    Movie m= new Movie();

    public void makeHttpCall(Activity callingActivity, String finalURL){
        Log.i(Tracking, "Inside makeHttpCall() of GetMovieDetails.java");
        this.callingActivity= callingActivity;
        this.finalURL= finalURL;
        new GetAllMovieDetails().execute();
//        Log.i(Tracking, "m= "+ m);
//        Log.i(Tracking, "m.getTitle()= "+ m.getTitle());
//        return m;
    }

    private class GetAllMovieDetails extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(callingActivity);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Tracking, "Inside doInBackground() of GetMovieDetails.java");
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(finalURL, ServiceHandler.GET);
            Log.i(Tracking, "finalURL= " + finalURL);
            Log.i(Tracking, "Act_DetailsPage. jsonStr= " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject everything = new JSONObject(jsonStr);

                    Log.i(Tracking, "JSONOBject everything= " + everything);
                    Log.i(Tracking, "everything.getString(JSON_Tag.TAG_TITLE))= " + everything.getString(JSON_Tags.TAG_TITLE));
                    m.setTitle(everything.getString(JSON_Tags.TAG_TITLE));
                    m.setYear(everything.getString(JSON_Tags.TAG_YEAR));
                    m.setRated(everything.getString(JSON_Tags.TAG_RATED));
                    m.setReleased(everything.getString(JSON_Tags.TAG_RELEASED));
                    m.setRuntime(everything.getString(JSON_Tags.TAG_RUNTIME));
                    Log.i(Tracking, "1");
                    m.setGenre(everything.getString(JSON_Tags.TAG_GENRE));
                    m.setDirector(everything.getString(JSON_Tags.TAG_DIRECTOR));
                    m.setWriter(everything.getString(JSON_Tags.TAG_WRITER));
                    m.setActors(everything.getString(JSON_Tags.TAG_ACTORS));
                    m.setPlot(everything.getString(JSON_Tags.TAG_PLOT));
                    m.setLanguage(everything.getString(JSON_Tags.TAG_LANGUAGE));
                    Log.i(Tracking, "2");
                    m.setCountry(everything.getString(JSON_Tags.TAG_COUNTRY));
                    m.setAwards(everything.getString(JSON_Tags.TAG_AWARDS));
                    m.setPosterURL(everything.getString(JSON_Tags.TAG_POSTER));
                    m.setMetascore(everything.getString(JSON_Tags.TAG_METASCORE));
                    m.setImdbRating(everything.getString(JSON_Tags.TAG_IMDB_RATING));
                    m.setImdbVotes(everything.getString(JSON_Tags.TAG_IMDB_VOTES));
                    Log.i(Tracking, "3");
                    m.setImdbID(everything.getString(JSON_Tags.TAG_IMDB_ID));
                    m.setType(everything.getString(JSON_Tags.TAG_TYPE));
                    m.setResponse(everything.getString(JSON_Tags.TAG_RESPONSE));

                    Log.i(Tracking, "4");
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

            //setDisplay();
        }
    }
}