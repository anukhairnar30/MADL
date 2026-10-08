package com.triptales.app;
import android.app.*;import android.content.*;import android.os.*;import android.widget.*;
public class TripDetailsActivity extends Activity{
 long id;TripTalesDatabase db;
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_trip_details);id=getIntent().getLongExtra("tripId",-1);db=new TripTalesDatabase(this);TripTalesDatabase.Trip t=db.getTrip(id);if(t==null){finish();return;}((TextView)findViewById(R.id.titleText)).setText(t.name);((TextView)findViewById(R.id.detailText)).setText("📍 "+t.destination+"\n\n📅 "+t.startDate+" → "+t.endDate+"\n\n"+t.notes);findViewById(R.id.journalButton).setOnClickListener(v->{Intent x=new Intent(this,JournalActivity.class);x.putExtra("tripId",id);startActivity(x);});findViewById(R.id.cameraButton).setOnClickListener(v->{Intent x=new Intent(this,CameraActivity.class);x.putExtra("tripId",id);startActivity(x);});}
}
