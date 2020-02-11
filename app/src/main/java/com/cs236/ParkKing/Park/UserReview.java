package com.cs207.ParkKing.Park;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sushma on 11/24/2016.
 */

public class UserReview extends AppCompatActivity {
    ArrayList<MovieReview> moviereview;
    ListView movielist;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        //create Array list of menu items
        moviereview = new ArrayList();

        moviereview.add(new MovieReview("MAJESTIC", "@drawable/avengerreview",5,"Excellent"));

        moviereview.add(new MovieReview("MALLESHWARAM", "@drawable/flypaperreview",3,"Very Good"));

        moviereview.add(new MovieReview("ELECTRONIC CITY", "@drawable/missionimpossible",2," Good"));

        moviereview.add(new MovieReview("YASHWANTHPUR", "@drawable/spidermanreview",1,"Average"));
        adapter=new CustomAdapter(this, moviereview);

        movielist=(ListView)findViewById(R.id.myListView);
        movielist.setAdapter(adapter);

    }


}
