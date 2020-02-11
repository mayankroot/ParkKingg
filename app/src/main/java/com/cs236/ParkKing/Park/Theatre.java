package com.cs207.ParkKing.Park;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.location.Location;
import android.widget.Toast;

public class Theatre extends AppCompatActivity {
    public int dist;
    public int distance1;
    TextView amcdist;
    TextView regaldist;
    TextView landmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);

        Bundle b = getIntent().getExtras();
        final int movie = b.getInt("movie_id");
        final String date = b.getString("date");

        Button a1 = (Button) findViewById(R.id.button9);
        Button AMC2 = (Button) findViewById(R.id.AMC2);
        Button AMC3 = (Button) findViewById(R.id.AMC3);
        Button AMC4 = (Button) findViewById(R.id.AMC4);
        Button AMC5 = (Button) findViewById(R.id.AMC5);

        Button RC1 = (Button) findViewById(R.id.RC1);
        Button RC2 = (Button) findViewById(R.id.RC2);
        Button RC3 = (Button) findViewById(R.id.RC3);
        Button RC4 = (Button) findViewById(R.id.RC4);

        Button LMC1 = (Button) findViewById(R.id.LMC1);
        Button LMC2 = (Button) findViewById(R.id.LMC2);
        Button LMC3 = (Button) findViewById(R.id.LMC3);
        Button LMC4 = (Button) findViewById(R.id.LMC4);
        Button LMC5 = (Button) findViewById(R.id.LMC5);

         amcdist = (TextView) findViewById(R.id.amcdist);
         regaldist = (TextView) findViewById(R.id.regaldist);
         landmark  = (TextView) findViewById(R.id.landmarkdist);

        distance1 = distance(12.9767, 77.5713);
        if(distance1 == 0) {
            Toast.makeText(getApplicationContext(), "Please Switch on GPS to find the Theatre Distance", Toast.LENGTH_SHORT).show();
            amcdist.setText(Integer.toString(distance1) + " mi");
            distance1 = distance(13.0031, 77.5643);
            regaldist.setText(Integer.toString(distance1) + " mi");
            distance1 = distance(13.0280, 77.5409);
            landmark.setText(Integer.toString(distance1) + " mi");
            final SwipeRefreshLayout swipe = (SwipeRefreshLayout) findViewById(R.id.activity_theatre);
            swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipe.setRefreshing(true);
                    ( new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipe.setRefreshing(false);
                            distance1 = distance(12.9767, 77.5713);
                            amcdist.setText(Integer.toString(distance1) + " km");
                            distance1 = distance(13.0031, 77.5643);
                            regaldist.setText(Integer.toString(distance1) + " km");
                            distance1 = distance(13.0280, 77.5409);
                            landmark.setText(Integer.toString(distance1) + " km");
                        }
                    },2000);
                }
            });
        }
        else{
            amcdist.setText(Integer.toString(distance1) + " km");
            distance1 = distance(13.0031, 77.5643);
            regaldist.setText(Integer.toString(distance1) + " km");
            distance1 = distance(13.0280, 77.5409);
            landmark.setText(Integer.toString(distance1) + " km");
        }

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 1");
                intent.putExtra("time", "11:55 AM");
                startActivity(intent);

            }
        });

        AMC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 1");
                intent.putExtra("time", "02.15 PM");
                startActivity(intent);

            }
        });

        AMC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 1");
                intent.putExtra("time", "05.30 PM");
                startActivity(intent);

            }
        });

        AMC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 1");
                intent.putExtra("time", "07.15 PM");
                startActivity(intent);

            }
        });

        AMC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 1");
                intent.putExtra("time", "09.15 PM");
                startActivity(intent);

            }
        });

        RC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 2");
                intent.putExtra("time", "12.05 PM");
                startActivity(intent);

            }
        });

        RC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 2");
                intent.putExtra("time", "04.15 PM");
                startActivity(intent);

            }
        });

        RC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 2");
                intent.putExtra("time", "07.05 PM");
                startActivity(intent);

            }
        });

        RC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 2");
                intent.putExtra("time", "19.15 PM");
                startActivity(intent);

            }
        });

        LMC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 3");
                intent.putExtra("time", "11.30 PM");
                startActivity(intent);

            }
        });

        LMC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 3");
                intent.putExtra("time", "02.45 PM");
                startActivity(intent);

            }
        });

        LMC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 3");
                intent.putExtra("time", "04.30 PM");
                startActivity(intent);

            }
        });

        LMC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 3");
                intent.putExtra("time", "07.05 PM");
                startActivity(intent);

            }
        });

        LMC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Theatre.this, SeatSelection.class);
                intent.putExtra("vehicle_id", movie);
                intent.putExtra("date", date);
                intent.putExtra("theatre", "AREA 3");
                intent.putExtra("time", "09.15 PM");
                startActivity(intent);

            }
        });

    }
        public int distance(double latitude, double longitude) {
            GPSTracker gps = new GPSTracker(this);

            if (gps.canGetLocation()) { // gps enabled} // return boolean true/false
                double lat2 = gps.getLatitude(); // returns latitude
                double lng2 = gps.getLongitude(); // returns longitude
                System.out.print(lat2 + " " + lng2);
                gps.stopUsingGPS();
            //    Toast.makeText(getApplicationContext(), "Your location" + lat2 + " \n" + lng2, Toast.LENGTH_SHORT).show();
                double earthRadius = 3958.75;
                double dLat = Math.toRadians(latitude - lat2);
                double dLng = Math.toRadians(longitude - lng2);
                double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(latitude)) *
                                Math.sin(dLng / 2) * Math.sin(dLng / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                 dist = (int) (earthRadius * c);
               // Toast.makeText(getApplicationContext(), "Your distance" + dist, Toast.LENGTH_LONG).show();
            }
            return dist;
        }
        /*Intent intent = new Intent(Theatre.this, SeatSelection.class);
        intent.putExtra("movie_id", movie);
        intent.putExtra("date", date);*/
}
