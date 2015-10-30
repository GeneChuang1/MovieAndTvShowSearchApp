package com.sample.genechuang.movieandtvsearch.ListViewPages;

//Created by GeneChuang on 10/16/2015.
//Gene Chuang owns the copyright to this code. Publication or distribution of this code is
//not allowed without Gene Chuang's written consent.
public class TvShow {
    private String mTitle, mDirector;
    private String mYear;
    private boolean mViewed= false;
    private String mPosterURL;
    private String imdbID;

    public TvShow(){
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle= title;
    }

    public String getYear(){
        return mYear;
    }

    public void setYear(String year){
        mYear= year;
    }

    public String getDirector (){
        return mDirector;
    }

    public void setDirector (String director){
        mDirector= director;
    }

    public boolean getViewed(){
        return mViewed;
    }

    public void setViewed(boolean viewed){
        mViewed= viewed;
    }

    public String getPosterURL(){
        return mPosterURL;
    }

    //public void setPoster(Drawable poster){
    public void setPoster(String poster){
        mPosterURL = poster;
    }

    public void setID(String id){ imdbID= id;}

    public String getID(){ return imdbID;}
}
