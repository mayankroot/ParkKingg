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


public class Fragment4 extends Fragment implements View.OnClickListener {
    ImageView btw4;
    ImageButton img1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab5, container, false);
        btw4 = (ImageView) rootView.findViewById(R.id.imageView40);
        btw4.setOnClickListener(this);
        img1 = (ImageButton) rootView.findViewById(R.id.imageButton40);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "YASHWANTHPUR");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        return rootView;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), Detail2.class);
        intent.putExtra("booktype", 04);
        startActivity(intent);
    }
}

