package com.triptales.app;
import android.app.*;import android.os.*;import android.widget.*;
public class AddTripActivity extends Activity{
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_add_trip);EditText n=findViewById(R.id.nameEdit),d=findViewById(R.id.destinationEdit),s=findViewById(R.id.startDateEdit),e=findViewById(R.id.endDateEdit),no=findViewById(R.id.notesEdit);findViewById(R.id.saveTripButton).setOnClickListener(v->{if(n.getText().toString().trim().isEmpty()||d.getText().toString().trim().isEmpty()){Toast.makeText(this,"Trip name and destination are required",Toast.LENGTH_SHORT).show();return;}new TripTalesDatabase(this).addTrip(n.getText().toString(),d.getText().toString(),s.getText().toString(),e.getText().toString(),no.getText().toString());Toast.makeText(this,"Trip saved to SQLite",Toast.LENGTH_SHORT).show();finish();});}
}
