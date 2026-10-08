package com.triptales.app;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF="TripTalesSession";
    private final SharedPreferences p;
    public SessionManager(Context c){p=c.getSharedPreferences(PREF,Context.MODE_PRIVATE);}
    public void login(String email,String name){p.edit().putBoolean("logged",true).putString("email",email).putString("name",name).apply();}
    public boolean isLoggedIn(){return p.getBoolean("logged",false);}
    public String getEmail(){return p.getString("email","");}
    public String getName(){return p.getString("name","Traveler");}
    public void logout(){p.edit().clear().apply();}
}
