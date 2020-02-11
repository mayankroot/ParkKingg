package com.cs207.ParkKing.Park;
import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class  CustomAdapter extends BaseAdapter {

    private final Activity context;
    private  ArrayList<MovieReview> moviereview=null;

    public CustomAdapter(Activity context, ArrayList<MovieReview> moviereview) {

        this.context=context;
        this.moviereview=moviereview;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public int getCount()
    {
        return moviereview.size();
    }
    @Override
    public Object getItem(int position) {
        return moviereview.get(position);
    }

    //set the text and image in the display to the position value from arraylist and set text and image to list
    public View getView(int position,View view,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layoutreview, null);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        Resources res = context.getResources();
        int resID = res.getIdentifier(moviereview.get(position).getmovieimage() , "drawable", context.getPackageName());

        imageView.setImageResource(resID);
        TextView textid=(TextView) rowView.findViewById(R.id.textid);
        textid.setText(String.valueOf(moviereview.get(position).getuser()));
        TextView textname=(TextView) rowView.findViewById(R.id.textname);
        textname.setText(moviereview.get(position).getmoviename());
        RatingBar textrate=(RatingBar) rowView.findViewById(R.id.textrate);
        int rate=moviereview.get(position).getmovierating();

          textrate.setNumStars(rate);
        return rowView;

    };
}