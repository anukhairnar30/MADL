package com.triptales.app;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class TripTalesDatabase extends SQLiteOpenHelper {
    private static final String DB="triptales.db"; private static final int VER=1;
    public TripTalesDatabase(Context c){super(c,DB,null,VER);}
    public void onCreate(SQLiteDatabase db){db.execSQL("CREATE TABLE trips(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,destination TEXT,startDate TEXT,endDate TEXT,notes TEXT)");db.execSQL("CREATE TABLE journals(id INTEGER PRIMARY KEY AUTOINCREMENT,tripId INTEGER,content TEXT,createdAt TEXT)");}
    public void onUpgrade(SQLiteDatabase db,int oldV,int newV){db.execSQL("DROP TABLE IF EXISTS trips");db.execSQL("DROP TABLE IF EXISTS journals");onCreate(db);}
    public long addTrip(String n,String d,String s,String e,String notes){ContentValues v=new ContentValues();v.put("name",n);v.put("destination",d);v.put("startDate",s);v.put("endDate",e);v.put("notes",notes);return getWritableDatabase().insert("trips",null,v);}
    public ArrayList<Trip> getTrips(){ArrayList<Trip> a=new ArrayList<>();Cursor c=getReadableDatabase().query("trips",null,null,null,null,null,"id DESC");while(c.moveToNext())a.add(new Trip(c.getLong(c.getColumnIndexOrThrow("id")),c.getString(c.getColumnIndexOrThrow("name")),c.getString(c.getColumnIndexOrThrow("destination")),c.getString(c.getColumnIndexOrThrow("startDate")),c.getString(c.getColumnIndexOrThrow("endDate")),c.getString(c.getColumnIndexOrThrow("notes"))));c.close();return a;}
    public Trip getTrip(long id){Cursor c=getReadableDatabase().query("trips",null,"id=?",new String[]{String.valueOf(id)},null,null,null);Trip t=null;if(c.moveToFirst())t=new Trip(id,c.getString(c.getColumnIndexOrThrow("name")),c.getString(c.getColumnIndexOrThrow("destination")),c.getString(c.getColumnIndexOrThrow("startDate")),c.getString(c.getColumnIndexOrThrow("endDate")),c.getString(c.getColumnIndexOrThrow("notes")));c.close();return t;}
    public void addJournal(long tripId,String content){ContentValues v=new ContentValues();v.put("tripId",tripId);v.put("content",content);v.put("createdAt",new Date().toString());getWritableDatabase().insert("journals",null,v);}
    public String latestJournal(long tripId){Cursor c=getReadableDatabase().query("journals",new String[]{"content"},"tripId=?",new String[]{String.valueOf(tripId)},null,null,"id DESC","1");String s="";if(c.moveToFirst())s=c.getString(0);c.close();return s;}
    public static class Trip{public long id;public String name,destination,startDate,endDate,notes;Trip(long i,String n,String d,String s,String e,String no){id=i;name=n;destination=d;startDate=s;endDate=e;notes=no;}}
}
