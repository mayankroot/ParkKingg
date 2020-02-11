package com.cs207.ParkKing.Park;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;



public class MovieConfirmation extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private final int CHECK_CODE = 0x1;
    private final int LONG_DURATION = 5000;
    private final int SHORT_DURATION = 1200;
     TextView MovieName = null;
    NotificationManager notificationManager;
    String speech;
    Bitmap bm;
    android.support.v7.app.NotificationCompat.Builder Builder;
    Notification notification;
    private Bundle bundle;
    public static final int ID = 45612;
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech myTTS = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movieconfirmation);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Builder = new android.support.v7.app.NotificationCompat.Builder(MovieConfirmation.this);
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        Log.i("inside screen","ok");

         MovieName=(TextView)findViewById(R.id.MovieName);
        final TextView theatre=(TextView)findViewById(R.id.theatrescreen);
        final TextView bookingid=(TextView)findViewById(R.id.bookingid);
        final TextView seats=(TextView)findViewById(R.id.seats);
        final TextView totalamount=(TextView)findViewById(R.id.totalamount);
        final TextView datetime=(TextView)findViewById(R.id.datetime);
        final ImageView imageview = (ImageView) findViewById(R.id.imageView3);
        Log.i("done select","ok");
        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MovieConfirmation.this, MainActivity.class);
                startActivity(home);
            }
        });

        int BookingId = getIntent().getIntExtra("booking_id",0);

        DatabaseHelper   d = new DatabaseHelper(getApplicationContext());

        Log.i("done inserting","ok");
        SQLiteDatabase db =d.getReadableDatabase();
        Cursor cursor =d.getData(db,String.valueOf(BookingId));
        Log.i("done selecting","ok");
        bookingid.setText(String.valueOf(BookingId));
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        while (cursor.moveToNext()) {
            System.out.print("data");
            String email=  cursor.getString(5);
            System.out.println(email);
            System.out.println(cursor.getString(6));
            String phoneno=  cursor.getString(6);
            System.out.println(phoneno);

            MovieName.setText(cursor.getString(0));
            theatre.setText(cursor.getString(1));
            totalamount.setText(String.valueOf(cursor.getInt(2)));
            datetime.setText(cursor.getString(3)+ ' ' + cursor.getString(4));
            seats.setText(cursor.getString(7));
            switch(MovieName.getText().toString())
            {
                case "MAJESTIC" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.images); break;
                case "MALLESHWARAM" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.mi55); break;
                case "ELECTRONIC CITY" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.avengers3); break;
                case "YASHWANTPUR": bm = BitmapFactory.decodeResource(getResources(),R.drawable.spiddy); break;
            }
            imageview.setImageBitmap(bm);
            speech = "Location:" + MovieName.getText().toString() + "  Area: " + theatre.getText().toString() + "  on " +  cursor.getString(3) + "at " +cursor.getString(4);
            try {
                //SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                //Date dt=sdf.parse(datetime.getText().toString());
                //System.out.println(dt);
                Calendar cal  = Calendar.getInstance();
                //cal.setTime(sdf.parse(datetime.getText().toString()));

                Uri EVENTS_URI = Uri.parse(getCalendarUriBase(this) + "events");
                ContentResolver cr = getContentResolver();

// event insert

                ContentValues values = new ContentValues();
                values.put("calendar_id", 1);
                values.put("title", "Vehicle Booking");
                values.put("allDay", 0);
                values.put("dtstart", cal.getTimeInMillis() + 11*60*1000); // event starts at 11 minutes from now
                values.put("dtend", cal.getTimeInMillis()+60*60*1000); // ends 60 minutes from now
                values.put("description", speech );
                values.put("eventTimezone", TimeZone.getDefault().getID());
                values.put("hasAlarm", 1);
                Uri event = cr.insert(EVENTS_URI, values);

// reminder insert
                Uri REMINDERS_URI = Uri.parse(getCalendarUriBase(this) + "reminders");
                cr = getContentResolver();
                values = new ContentValues();
                values.put( "event_id", Long.parseLong(event.getLastPathSegment()));
                values.put( "method", 1 );
                values.put( "minutes", 5);
                cr.insert( REMINDERS_URI, values );
            }
            catch(Exception e)
            {
                Toast.makeText(getApplicationContext(), "Reminder Could not be set",
                        Toast.LENGTH_SHORT).show();
            }




            try {
                new SendEmailAsyncTask().execute(email, bookingid.getText().toString(), MovieName.getText().toString(), theatre.getText().toString(), datetime.getText().toString(), seats.getText().toString(), totalamount.getText().toString());
                Toast.makeText(getApplicationContext(), "Email Sent Succesfully",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                Toast.makeText(getApplicationContext(), "Error in Sending Email",
                        Toast.LENGTH_SHORT).show();
            }

            sendSMS(phoneno, "Location: " + MovieName.getText().toString() + " Area:" + theatre.getText().toString() +
                    "  Seats:" + seats.getText().toString() + " Total Amount: Rs." + totalamount.getText().toString() + " Date/Time:" + datetime.getText().toString());

        }

    }
    private String getCalendarUriBase(Activity act) {

        String calendarUriBase = null;
        Uri calendars = Uri.parse("content://calendar/calendars");
        Cursor managedCursor = null;
        try {
            managedCursor = act.managedQuery(calendars, null, null, null, null);
        } catch (Exception e) {
        }
        if (managedCursor != null) {
            calendarUriBase = "content://calendar/";
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars");
            try {
                managedCursor = act.managedQuery(calendars, null, null, null, null);
            } catch (Exception e) {
            }
            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/";
            }
        }
        return calendarUriBase;
    }

    private void sendSMS( String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, "null", message.trim(), sentPI, deliveredPI);
    }

    class SendEmailAsyncTask extends AsyncTask<String, Void, Boolean> {
        GMailSender m = new GMailSender("mayank7996123809@gmail.com", "mayank$$123");

        public SendEmailAsyncTask() {

        }

        @Override
        protected Boolean doInBackground(String... params) {
            if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
            try {
                m.sendMail("Vehicle Booking",
                        "Booking Id : "+params[1]+" VehicleName: "+params[2]+" Vehicle: "+params[3]+" Date/Time: "+params[4]+" No Of Slots: "+params[5]+" TotalAmount:" +params[6],
                        "mayank7996123809@gmail.com",
                        params[0]);
                return true;
            } catch (AuthenticationFailedException e) {
                Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
                e.printStackTrace();
                return false;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    private void speakWords(String speech) {
        //speak straight away
        Log.d("Speak Words Called"," ");
        myTTS.setPitch(1.1f);
        myTTS.setSpeechRate(0.8f);
        myTTS.speak(speech,TextToSpeech.QUEUE_FLUSH,null,null);

    }
    public void notification(String message)           //Notification tab to print the notification
    {

        Log.d("Notification started", " ");
        switch(MovieName.getText().toString())
        {
            case "MAJESTIC" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.images); break;
            case "MALLESHWARAM" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.mi55); break;
            case "ELECTRONIC CITY" : bm = BitmapFactory.decodeResource(getResources(),R.drawable.avengers3); break;
            case "YASHWANTHPUR": bm = BitmapFactory.decodeResource(getResources(),R.drawable.spiddy); break;
        }

        notification = Builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true).setLargeIcon(bm)
                .setContentTitle("Vehicle Booked " ).setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).build();
        NotificationCompat.InboxStyle inboxstyle = new NotificationCompat.InboxStyle();
        inboxstyle.setBigContentTitle("Booking Confirmation");
        inboxstyle.addLine(message);
        Builder.setStyle(inboxstyle);
        notificationManager.notify(ID, notification);

        speakWords(speech);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS

                myTTS = new TextToSpeech(MovieConfirmation.this, this);
                Log.d("Initialised TTS", " ");

            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
    public void onInit(int initStatus) {
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
            Log.d("Success" ," ");
            notification(speech);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(MovieConfirmation.this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
            Log.d("Failed" , " ");
        }
    }
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
