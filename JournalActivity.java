package com.triptales.app;
import android.app.*;import android.os.*;import android.widget.*;
public class JournalActivity extends Activity{
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_journal);long id=getIntent().getLongExtra("tripId",-1);TripTalesDatabase db=new TripTalesDatabase(this);EditText e=findViewById(R.id.journalEdit);e.setText(db.latestJournal(id));findViewById(R.id.saveJournalButton).setOnClickListener(v->{db.addJournal(id,e.getText().toString());Toast.makeText(this,"Journal saved to SQLite + ready for Firebase sync",Toast.LENGTH_SHORT).show();finish();});}
}
