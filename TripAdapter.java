package com.triptales.app;

import android.view.*;import android.widget.*;import androidx.annotation.NonNull;import androidx.recyclerview.widget.RecyclerView;import java.util.*;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.H>{
    public interface Click{void onClick(TripTalesDatabase.Trip t);} private final ArrayList<TripTalesDatabase.Trip> data;private final Click click;
    public TripAdapter(ArrayList<TripTalesDatabase.Trip>d,Click c){data=d;click=c;}
    @NonNull public H onCreateViewHolder(@NonNull ViewGroup p,int v){return new H(LayoutInflater.from(p.getContext()).inflate(R.layout.item_trip,p,false));}
    public void onBindViewHolder(@NonNull H h,int i){TripTalesDatabase.Trip t=data.get(i);h.name.setText(t.name);h.dest.setText("📍 "+t.destination);h.dates.setText(t.startDate+" → "+t.endDate);h.itemView.setOnClickListener(v->click.onClick(t));}
    public int getItemCount(){return data.size();}
    static class H extends RecyclerView.ViewHolder{TextView name,dest,dates;H(View v){super(v);name=v.findViewById(R.id.tripName);dest=v.findViewById(R.id.tripDestination);dates=v.findViewById(R.id.tripDates);}}
}
