package com.cs207.ParkKing.Park;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Suraj Didwania on 04-11-2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Movie.db";
    public static final String TABLE_NAME1 = "Movies";
    public static final String TABLE_NAME3 = "Movies_Booked";
    public static final String TABLE_NAME4 = "User_Info";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 +" (NAME VARCHAR, MOVIE_ID INTEGER PRIMARY KEY AUTOINCREMENT,INFORMATION VARCHAR, RATINGS INTEGER)");
        db.execSQL("create table " + TABLE_NAME3 +" (BOOKINGID INTEGER PRIMARY KEY AUTOINCREMENT,MOVIE_ID INTEGER,TIMINGS TIME,THEATRES VARCHAR,EMAIL VARCHAR,PRICE INTEGER,SHOW_DATE DATE, PHONE  VARCHAR,SEATS_NO  VARCHAR,FOREIGN KEY(MOVIE_ID) REFERENCES Movies(MOVIE_ID))");
        db.execSQL("create table " + TABLE_NAME4 +" (USER_ID VARCHAR, NAME VARCHAR,EMAIL VARCHAR )");
        ContentValues content = new ContentValues();
        db.execSQL("insert into " + TABLE_NAME1 +" (NAME,INFORMATION, RATINGS)" + "VALUES" + "('MAJESTIC','m','5')");
        db.execSQL("insert into " + TABLE_NAME1 + " (NAME,INFORMATION,RATINGS)"+ "VALUES" + "('MALLESHWARAM','mm','4')");
        db.execSQL("insert into " + TABLE_NAME1 + " (NAME,INFORMATION,RATINGS)"+ "VALUES"+ "('ELECTRONIC CITY','ec','3')");
        db.execSQL("insert into " + TABLE_NAME1 + " (NAME,INFORMATION,RATINGS)"+ "VALUES"+ "('YASHWANTHPUR','ya','4')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME3);
        onCreate(db);
    }
    //Sushma Database part
    public boolean insertmoviebooked(int id, String Seats, String theatre, String time, String date, int price, String Email, String phone)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("Movie_ID",id);
        content.put("TIMINGS",time);
        content.put("THEATRES",theatre);
        content.put("SHOW_DATE", date);
        content.put("PRICE", price);
        content.put("EMAIL",Email);
        content.put("PHONE",phone);
        content.put("SEATS_NO",Seats);
        long result = db.insert(TABLE_NAME3,null,content);
        if(result == -1) return false; else return true;
    }
    public boolean insertuser(String id,String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("USER_ID",id);
        content.put("NAME", name);
        content.put("EMAIL", email);
        long result = db.insert(TABLE_NAME4, null, content);
        if (result == -1) return false;
        else return true;
    }


    public Cursor getmaxbooking(SQLiteDatabase db)
    {
        Cursor cursor;
        String sql = "Select max(BOOKINGID) from "  + TABLE_NAME3;
        cursor = db.rawQuery(sql,null);
        return cursor;
    }
    public Cursor getData(SQLiteDatabase db,String  Booking_ID)
    {
        Cursor cursor;
        String sql="SELECT t1.NAME,t2.THEATRES,t2.PRICE,t2.SHOW_DATE,t2.TIMINGS,t2.EMAIL,t2.PHONE,t2.SEATS_NO FROM Movies_Booked t2,Movies t1 WHERE  t1.MOVIE_ID=t2.MOVIE_ID AND t2.BOOKINGID=? ";

        cursor = db.rawQuery(sql,new String[]{Booking_ID});
        System.out.println(Booking_ID);

        return cursor;
    }
    //Suraj Part
    public Cursor getseats( SQLiteDatabase db, String Theatre, String date, String time, int movie_id)
    {
        Cursor cursor=null;
        System.out.print(Theatre + date + time + movie_id);
        String sql = "select SEATS_NO from " + TABLE_NAME3 + " where MOVIE_ID = ? and THEATRES = ? and TIMINGS = ? AND SHOW_DATE = ?";
            cursor = db.rawQuery(sql, new String[]{Integer.toString(movie_id), Theatre, time, date});
            Cursor md = getAllDataMovies();
            while(md.moveToNext())
            {
                System.out.print(md);
            }
            System.out.print(cursor);
            System.out.print(cursor.getCount());

        return cursor;

    }
    public Cursor getAllDataMovies()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " +TABLE_NAME1;
        Cursor res = db.rawQuery(sql,null);
        return res;

    }

    public Cursor getMoviename(SQLiteDatabase db, String movie_id)
    {
        Cursor cursor;
       String sql = "select NAME FROM "+ TABLE_NAME1+ " where MOVIE_ID = ?";
        cursor = db.rawQuery(sql, new String[]{movie_id});
        System.out.print(movie_id);
        return cursor ;
    }

}

