package com.cs207.ParkKing.Park;

public  class MovieDetails {

    private String moviename;
    private String movieimage;
    private String moviedata;

    public MovieDetails(String moviename,String movieimage,String moviedata) {
        this.moviename = moviename;
        this.movieimage = movieimage;
        this.moviedata = moviedata;
    }

    public String getmoviedata() {
        return moviedata;
    }

    public void setmoviedata(String moviedata) {
        this.moviedata = moviedata;
    }


    public String getmoviename() {
        return moviename;
    }

    public void setmoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getmovieimage() {
        return movieimage;
    }

    public void setmovieimage(String menuimage) {
        this.movieimage = menuimage;
    }


    @Override
    public String toString() {
        return this.moviename + ". " + this.movieimage;
    }

}

