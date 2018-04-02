package com.sample.genechuang.movieandtvsearch.Controller.VariablesAndSettings;

/**
 * Created by GeneChuang on 10/16/2015.
 */
public class SavedUserSearch {
    private String movieName= "";
    private String movieYear= "";
    private String tvShowName= "";
    private String tvShowYear= "";
    private Tags.ENUM_MOVIE_OR_TV userBookmark;

    private static SavedUserSearch ourInstance = new SavedUserSearch();

    public static SavedUserSearch getInstance() {
        return ourInstance;
    }

    private SavedUserSearch() {
    }

    public void setMovieName(String name) {movieName= name;}
    public String getMovieName() {return movieName;}

    public void setMovieYear(String year) {movieYear= year;}
    public String getMovieYear() {return movieYear;}


    public void setTvShowsName(String name) {tvShowName= name;}
    public String getTvShowsName() {return tvShowName;}

    public void setTvShowsYear(String year) {tvShowYear= year;}
    public String getTvShowsYear() {return tvShowYear;}


    public void setUserBookmark( Tags.ENUM_MOVIE_OR_TV movie_or_tv){
        userBookmark= movie_or_tv;
    }
    public Tags.ENUM_MOVIE_OR_TV getUserBookmark(){
        return userBookmark;
    }
}
