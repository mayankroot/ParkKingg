package com.cs207.ParkKing.Park;

public  class MovieReview {

    private String moviename;
    private String movieimage;
    private String user;
    private int rating;

    public MovieReview(String moviename,String movieimage,int rating,String user) {
        this.moviename = moviename;
        this.movieimage = movieimage;
        this.user = user;
        this.rating = rating;

    }


    public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
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

    public int getmovierating() {
        return rating;
    }

    public void setmovierating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return this.moviename + ". " + this.movieimage;
    }

}

