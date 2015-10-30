package com.sample.genechuang.movieandtvsearch.ListViewPages;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sample.genechuang.movieandtvsearch.ListViewPages.ImageUtilities.AppController;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.JSON_Tags;
import com.sample.genechuang.movieandtvsearch.VariablesAndSettings.SavedUserSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class Frag_TvShowsList extends Fragment {
    private ProgressDialog pDialog;
    private List<TvShow> tvShowList = new ArrayList<TvShow>();
    private ListView listView;
    private TvShows_CustomListAdapter adapter;

    // URL to get contacts JSON
    private String baseString= "http://www.omdbapi.com/?";
    private String listOfTvShowsString ="";

    private String tvShowName;
    private String tvShowYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        initializeList();

        listView = (ListView) view.findViewById(R.id.list);
        adapter = new TvShows_CustomListAdapter(getActivity(), tvShowList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
        getActivity().getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));

        System.out.println("listOfTvShowsString= " + listOfTvShowsString);

        // Creating volley request obj
        JsonObjectRequest movieReq = new JsonObjectRequest(listOfTvShowsString, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObj) {
                        System.out.println("jsonObj= " + jsonObj);
                        try {
                            JSONArray everything = jsonObj.getJSONArray(JSON_Tags.TAG_JSON_ARRAY);
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < everything.length(); i++) {
                                JSONObject itemObjects = everything.getJSONObject(i);

                                TvShow show = new TvShow();
                                show.setTitle(itemObjects.getString(JSON_Tags.TAG_TITLE));

                                show.setPoster(itemObjects.getString(JSON_Tags.TAG_POSTER));
                                show.setYear(itemObjects.getString(JSON_Tags.TAG_YEAR));

                                String id= itemObjects.getString(JSON_Tags.TAG_ID);
                                show.setID(id);

                                GetDirector getDirector= new GetDirector(id);
                                String director= getDirector.getDirector();
                                show.setDirector(director);

                                // adding the show to the list of shows
                                tvShowList.add(show);
                           }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "No tv shows found", Toast.LENGTH_SHORT);
                            toast.show();
                            hidePDialog();
                            System.out.println("Frag_TvShowsList exception="+ e);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Frag_TvShowsList error= " + error);
                hidePDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        return view;
    }

    private void initializeList() {
        tvShowName = SavedUserSearch.getInstance().getTvShowsName();
        tvShowYear = SavedUserSearch.getInstance().getTvShowsYear();

        listOfTvShowsString = baseString+ "s="+ tvShowName +"&y="+ tvShowYear +"&plot=full"+"&r=json"+ "&type=series";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
