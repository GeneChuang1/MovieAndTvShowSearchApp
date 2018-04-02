package com.sample.genechuang.movieandtvsearch.View.ListViewPages;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sample.genechuang.movieandtvsearch.View.ListViewPages.ImageUtilities.AppController;
import com.sample.genechuang.movieandtvsearch.Model.TvShow;
import com.sample.genechuang.movieandtvsearch.R;
import com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings.Tags;

import java.util.List;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class TvShows_CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<TvShow> tvShowItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    TvShow show;

    SharedPreferences shared_prefs;

    public TvShows_CustomListAdapter(Activity activity, List<TvShow> tvShowItems) {
        this.activity = activity;
        this.tvShowItems = tvShowItems;
    }

    @Override
    public int getCount() {
        return tvShowItems.size();
    }

    @Override
    public Object getItem(int location) {
        return tvShowItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Getting movie data for this one row/item in the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        show= tvShowItems.get(position);

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        // thumbnail image
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        thumbNail.setImageUrl(show.getPosterURL(), imageLoader);

        // title
        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(show.getTitle());

        // release year
        TextView year = (TextView) convertView.findViewById(R.id.year);
        year.setText(String.valueOf(show.getYear()));

        //the director
        TextView director = (TextView) convertView.findViewById(R.id.director);
        director.setText(show.getDirector());

        //Creates an object that can RETRIEVE shared preferences' data.
        shared_prefs= activity.getSharedPreferences(Tags.SHARED_PREF, 0);
        //Lets user know if this movie/show has been viewed before
        TextView viewed = (TextView) convertView.findViewById(R.id.viewed);
        String sViewed= shared_prefs.getString(show.getID(), Tags.SHARED_PREF_NOT_VIEWED);
        viewed.setText(sViewed);

        Button viewButton = (Button) convertView.findViewById(R.id.list_button);
        viewButton.setOnClickListener(new TvShow_CustomOnClickListener(position, (TvShow) show, activity, viewed));

        return convertView;
    }
}