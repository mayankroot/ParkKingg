package com.cs207.ParkKing.Park;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Fragment2 extends Fragment implements View.OnClickListener {

    ImageView btw2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab3, container, false);
        btw2 = (ImageView) rootView.findViewById(R.id.imageView20);
        btw2.setOnClickListener(this);
        ImageButton share = (ImageButton) rootView.findViewById(R.id.imageButton20);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "MALLESHWARAM");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
        return rootView;

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), Detail2.class);
        intent.putExtra("booktype", 02);
        startActivity(intent);
    }
}
