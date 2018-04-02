package com.sample.genechuang.movieandtvsearch.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.Model.Movie;
import com.sample.genechuang.movieandtvsearch.View.ListViewPages.ImageUtilities.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


//Gene's attempt at MVC. MVC doesn't work that well in Android because I still need to pass it an Activity, so
//I need to know beforehand what UI elements (textviews) I need to update.
public class Movies_HTTPService {
    private static final String Tracking = "Tracking";
    private List<Movie> movieList = new ArrayList<Movie>();
    private ProgressDialog pDialog;
    Activity callingActivity;

    public List<Movie> getListOfMovies(String moviesListURL, Activity callingActivity) {
        pDialog = new ProgressDialog(callingActivity);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // Creating volley request obj
        JsonObjectRequest movieReq = new JsonObjectRequest(moviesListURL, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObj) {
                        Log.i(Tracking, "Inside onResponse() of Movies_HTTPService.java");
                        Log.i(Tracking, "jsonObj= " + jsonObj);
                        try {
                            JSONArray everything = jsonObj.getJSONArray(JSON_Tags.TAG_JSON_ARRAY);

                            // Parsing json
                            for (int i = 0; i < everything.length(); i++) {
                                Log.i(Tracking, "1");
                                JSONObject itemObjects = everything.getJSONObject(i);
                                Log.i(Tracking, "2");

                                Movie movie = new Movie();
                                movie.setTitle(itemObjects.getString(JSON_Tags.TAG_TITLE));
                                movie.setPosterURL(itemObjects.getString(JSON_Tags.TAG_POSTER));
                                movie.setYear(itemObjects.getString(JSON_Tags.TAG_YEAR));
                                String id = itemObjects.getString(JSON_Tags.TAG_ID);
                                movie.setImdbID(id);

                                GetDirector getDirector = new GetDirector(id);
                                String director = getDirector.getDirector();
                                movie.setDirector(director);

                                // adding movie to the list of movies
                                movieList.add(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            //Toast toast = Toast.makeText(callingActivity.getApplicationContext(), "No movies found", Toast.LENGTH_SHORT);
                            //toast.show();
                            hidePDialog();
                            Log.i(Tracking, "Frag_MoviesList exception=" + e);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Log.i(Tracking, "3");
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        //adapter.notifyDataSetChanged();
                        Log.i(Tracking, "End of onResponse() of Frag_MovieList.java");
                        hidePDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(Tracking, "error= " + error);
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);

        return movieList;
    }  //End of getListOfMovies()

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            Log.i(Tracking, "pDialog.dismiss()");
            pDialog = null;
        }
    }
}
