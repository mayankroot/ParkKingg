package com.cs207.ParkKing.Park;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class Confirmation extends Activity {
    ArrayList<String> arr= new ArrayList<String>();
    final Context context = this;
    SQLiteDatabase sql;
    DatabaseHelper db;
     int maximum=0;
    int sum=0,sum1 = 0;
    int mul,total_price;
    EditText email;
    EditText phone;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        db = new DatabaseHelper(getApplicationContext());
        sql = db.getWritableDatabase();
        Toast toast = Toast.makeText(getApplicationContext(), "Confirmation Page", Toast.LENGTH_LONG);
        Intent intent = getIntent();
        Bundle seat_info = intent.getBundleExtra("seat_information");
        final String movie_id = intent.getStringExtra("movie_id");
        final String date1 = intent.getStringExtra("date");
        final String time1 = intent.getStringExtra("time");
        final String theatre1 = intent.getStringExtra("theatre");

        ArrayList<String> seating = seat_info.getStringArrayList("seats");
        ArrayList<String> total = seat_info.getStringArrayList("total");
        String movie_name = null;
        System.out.print(movie_id);
        String seat_number = total.get(0);

        System.out.println("The seat number value is" + seat_number);


        total_price = Integer.parseInt(seat_number) * 10;

        String price_final = "Rs" + Integer.toString(total_price);
        Cursor movie = db.getMoviename(sql, movie_id);

        while (movie.moveToNext()) {
            movie_name = movie.getString(0);

        }
        arr.add(0, movie_name);
        int len = seating.size();
        arr.add(1, theatre1);
        arr.add(2, date1);
        arr.add(3, time1);
        arr.add(4, price_final);

        final TextView seats = (TextView) findViewById(R.id.noofseats);
        final TextView theatre = (TextView) findViewById(R.id.theascr);
        TextView date = (TextView) findViewById(R.id.date);
        TextView time = (TextView) findViewById(R.id.time);
        TextView price = (TextView) findViewById(R.id.price);
        email = (EditText) findViewById(R.id.emailid);
        phone = (EditText) findViewById(R.id.phone);
        SpannableString styledString = new SpannableString(arr.get(0));
        styledString.setSpan(new StyleSpan(Typeface.BOLD), 0, arr.get(0).length(), 0);

        TextView tv = (TextView) findViewById(R.id.textView16);
        tv.setText(styledString);
        ImageView imageview = (ImageView) findViewById(R.id.imageView3);
        switch (Integer.parseInt(movie_id)) {
            case 1:
                imageview.setImageResource(R.drawable.images);
                break;
            case 2:
                imageview.setImageResource(R.drawable.mi55);
                break;
            case 3:
                imageview.setImageResource(R.drawable.avengers3);
                break;
            case 4:
                imageview.setImageResource(R.drawable.spiddy);
                break;
        }
        seats.setText("");
        for (int i = 0; i < len; i++) {
            seats.setText(seats.getText() + seating.get(i));
            if (i != len - 1) {
                seats.setText(seats.getText() + ",");
            }
        }
        theatre.setText(arr.get(1));
        time.setText(arr.get(3));
        date.setText(arr.get(2));
        price.setText(arr.get(4));
        //Passing info to MovieConfirmation Screen
        final Bundle bundle = new Bundle();
        bundle.putString("movie_id", movie_id);
        bundle.putString("date", date1);
        bundle.putBundle("seat_information", seat_info);
        bundle.putString("time", time1);
        bundle.putString("theatre", theatre1);

        bundle.putString("price", price.getText().toString());
        Button bktkt = (Button) findViewById(R.id.pay);
        bktkt.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                if (!isValidEmaillId(email.getText().toString().trim())||(phone.getText().toString().length() != 10)) {
                    Toast.makeText(getApplicationContext(), "Email/Phone number not valid", Toast.LENGTH_SHORT).show();
                } else {
                    final String Email = email.getText().toString();
                    final String phoneno = phone.getText().toString();

                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.activity_user_information);
                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    // params.height = WindowManager.LayoutParams.FILL_PARENT;
                    params.width = WindowManager.LayoutParams.FILL_PARENT;
                    dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
                    final EditText namecard = (EditText) dialog.findViewById(R.id.nameoncard);
                    final EditText creditcard = (EditText) dialog.findViewById(R.id.creditcard);
                    final EditText cvvno = (EditText) dialog.findViewById(R.id.cvv);
                    final EditText valid = (EditText) dialog.findViewById(R.id.vt1);
                    final EditText valid1 = (EditText) dialog.findViewById(R.id.vt2);

                    Button order = (Button) dialog.findViewById(R.id.completeorder);
                    order.setText("Complete Order ( Total " + arr.get(4) + ")");
                    // if button is clicked, close the custom dialog
                    order.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            ArrayList<String> reversedarry = new ArrayList<String>();
                            if ((namecard.getText().toString().isEmpty()) || (creditcard.getText().toString().isEmpty()) || (cvvno.getText().toString().isEmpty()) || (valid.getText().toString().isEmpty())) {
                                Toast.makeText(getApplicationContext(), "Please insert the data", Toast.LENGTH_SHORT).show();
                            } else {
                                    int booking_id = 0;
                                    Log.d("phone no", phoneno);
                                    Cursor max = db.getmaxbooking(sql);
                                    if (max != null && max.getCount() > 0) {
                                        while (max.moveToNext()) {
                                            maximum = max.getInt(0);
                                        }
                                    }
                                    if (db.insertmoviebooked(Integer.parseInt(movie_id), seats.getText().toString(), theatre1, time1, date1, 10, Email, phoneno)) {

                                        Intent intent;
                                        intent = new Intent(context, MovieConfirmation.class);
                                        intent.putExtra("booking_id", (maximum + 1));
                                        context.startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error in Movie Booking. Please try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                    });
                    dialog.show();
                }
            }
        });

    }

    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    }

