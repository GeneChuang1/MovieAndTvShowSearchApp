package com.sample.genechuang.movieandtvsearch.ListViewPages;

import android.os.AsyncTask;

import com.sample.genechuang.movieandtvsearch.Utilities.ServiceHandler;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.JSON_Tags;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by GeneChuang on 10/16/2015.
 */
public class GetDirector {
    String id;
    private String baseURL = "http://www.omdbapi.com/?i=";
    private String finalURL = "";
    String theDirector;

    public GetDirector(String imdbID) throws ExecutionException, InterruptedException {
        id= imdbID;
        finalURL = baseURL + id + "&plot=full&r=json"+ ServiceHandler.apiKey;
        //System.out.println("GetDirector. finalURL= " + finalURL);
        theDirector = new GetDirectorInfo().execute().get();
    }

    public String getDirector (){
        return theDirector;
    }

    private class GetDirectorInfo extends AsyncTask<Void, Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            String directorName = null;

            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(finalURL, ServiceHandler.GET);
            System.out.println("GetDirector. jsonStr= " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject everything = new JSONObject(jsonStr);

                    directorName = everything.getString(JSON_Tags.TAG_DIRECTOR);
                    //System.out.println("directorName= "+ directorName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Inside GetDirector. Couldn't get any data from the url");
            }
            return directorName;
        }
    }
}
