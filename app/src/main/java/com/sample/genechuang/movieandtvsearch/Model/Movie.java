package com.sample.genechuang.movieandtvsearch.Model;

/**
 * Created by GeneChuang on 10/13/2015.
 */
public class Movie {
    private String title, director;
    private String year;
    private boolean viewed = false;
    private String posterURL;
    private String imdbID;

    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String metascore;
    private String ImdbRating;
    private String imdbVotes;
    private String type;
    private String response;

    //private Drawable mPosterURL;

    public Movie() {
    }

//----------Setters---------------------------------
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
        ImdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResponse(String response) {
        this.response = response;
    }
//--------------------Getters-------------------------------------

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }

    public boolean isViewed() {
        return viewed;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return ImdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getType() {
        return type;
    }

    public String getResponse() {
        return response;
    }
}
