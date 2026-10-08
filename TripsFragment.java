package com.triptales.app;
import android.content.*;import android.os.*;import android.view.*;import androidx.fragment.app.Fragment;import androidx.recyclerview.widget.*;
public class TripsFragment extends Fragment{
 TripTalesDatabase db;RecyclerView rv;
 public View onCreateView(LayoutInflater i,ViewGroup c,Bundle b){View v=i.inflate(R.layout.fragment_trips,c,false);rv=v.findViewById(R.id.tripsRecycler);rv.setLayoutManager(new LinearLayoutManager(requireContext()));db=new TripTalesDatabase(requireContext());v.findViewById(R.id.addTripButton).setOnClickListener(x->startActivity(new Intent(requireContext(),AddTripActivity.class)));load();return v;}
 public void onResume(){super.onResume();if(rv!=null)load();}void load(){rv.setAdapter(new TripAdapter(db.getTrips(),t->{Intent x=new Intent(requireContext(),TripDetailsActivity.class);x.putExtra("tripId",t.id);startActivity(x);}));}
}
