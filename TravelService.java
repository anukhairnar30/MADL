package com.triptales.app;
import android.app.*;import android.content.*;import android.os.*;import androidx.core.app.NotificationCompat;
public class TravelService extends Service{
 static final String CH="trip_service";
 public void onCreate(){super.onCreate();if(Build.VERSION.SDK_INT>=26){NotificationChannel c=new NotificationChannel(CH,"TripTales Travel Service",NotificationManager.IMPORTANCE_LOW);getSystemService(NotificationManager.class).createNotificationChannel(c);}Notification n=new NotificationCompat.Builder(this,CH).setContentTitle("TripTales Travel Service").setContentText("Travel reminders are active").setSmallIcon(R.drawable.ic_launcher).build();startForeground(101,n);}
 public int onStartCommand(Intent i,int flags,int id){return START_STICKY;}public android.os.IBinder onBind(Intent i){return null;}public void onDestroy(){super.onDestroy();}
}
