package com.triptales.app;
import android.os.*;import android.view.*;import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
public class ExploreFragment extends Fragment{
 public View onCreateView(LayoutInflater i,ViewGroup c,Bundle b){View v=i.inflate(R.layout.fragment_explore,c,false);TabLayout tabs=v.findViewById(R.id.exploreTabs);tabs.addTab(tabs.newTab().setText("Map"));tabs.addTab(tabs.newTab().setText("Weather"));if(b==null)show(new MapFragment());tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){public void onTabSelected(TabLayout.Tab t){show(t.getPosition()==0?new MapFragment():new WeatherFragment());}public void onTabUnselected(TabLayout.Tab t){}public void onTabReselected(TabLayout.Tab t){}});return v;}
 void show(Fragment f){requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.exploreContainer,f).commit();}
}
