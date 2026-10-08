package com.triptales.app;
import android.app.*;import android.content.*;import android.os.*;import android.view.*;
public class SplashActivity extends Activity{
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_splash);new Handler().postDelayed(()->{SessionManager s=new SessionManager(this);startActivity(new Intent(this,s.isLoggedIn()?MainActivity.class:LoginActivity.class));finish();},1400);}
}
