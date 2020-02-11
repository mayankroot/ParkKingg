package com.cs207.ParkKing.Park;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.maxDate;
import static android.R.attr.minDate;
import static java.lang.System.currentTimeMillis;


public class Detail2 extends Activity {

    private TextView Output;
    private Button changeDate;
    private int year;
    private int month;
    private int day;
    public String date;
    DatePickerDialog dpd;
    static final int DATE_PICKER_ID = 1111;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        Output = (TextView) findViewById(R.id.tvDate);
        changeDate = (Button) findViewById(R.id.btnChangeDate);
        TextView tv = (TextView) findViewById(R.id.textView8);
        ImageView img = (ImageView) findViewById(R.id.imageView6);
        Bundle b = getIntent().getExtras();
        final int movie = b.getInt("booktype");

        if(movie == 01){
            tv.setText("MAJESTIC");

            Drawable myDrawable = getResources().getDrawable(R.drawable.image1);
            img.setImageDrawable(myDrawable);

        }else if (movie == 02){

            tv.setText("MALLESHWARAM");

            Drawable myDrawable = getResources().getDrawable(R.drawable.mi5);
            img.setImageDrawable(myDrawable);



        }else if (movie == 03){

            tv.setText("ELECTRONIC CITY");

            Drawable myDrawable = getResources().getDrawable(R.drawable.avenger);
            img.setImageDrawable(myDrawable);

        }else if (movie == 04){

            tv.setText("YASHWANTPUR");

            Drawable myDrawable = getResources().getDrawable(R.drawable.spiderman);
            img.setImageDrawable(myDrawable);

        }

        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);



        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);

            }

        });

        Button btw = (Button) findViewById(R.id.next);


        btw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date!=null) {
                    Intent intent = new Intent(Detail2.this, Theatre.class);
                    intent.putExtra("movie_id", movie);
                    intent.putExtra("date", date);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Select the Date",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                Calendar max = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                dpd=new DatePickerDialog(this, pickerListener, year, month,day);
                c.add(Calendar.DAY_OF_MONTH, 1);
                dpd.getDatePicker().setMinDate(c.getTimeInMillis());
                max.add(Calendar.DAY_OF_MONTH, 50);
                dpd.getDatePicker().setMaxDate(max.getTimeInMillis());

                return dpd;

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
            date = Integer.toString(month + 1) + "/" + Integer.toString(day) + "/"  + Integer.toString(year);

            // Show selected date
            Output.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };
}
