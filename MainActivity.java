package com.triptales.app;
import android.os.*;import androidx.appcompat.app.AppCompatActivity;import androidx.fragment.app.Fragment;import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity{
 BottomNavigationView nav;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);nav=findViewById(R.id.bottomNavigation);nav.setOnItemSelectedListener(i->{Fragment f;if(i.getItemId()==R.id.nav_trips)f=new TripsFragment();else if(i.getItemId()==R.id.nav_explore)f=new ExploreFragment();else if(i.getItemId()==R.id.nav_profile)f=new ProfileFragment();else f=new HomeFragment();getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,f).commit();return true;});if(b==null)nav.setSelectedItemId(R.id.nav_home);}
 public void selectProfile(){nav.setSelectedItemId(R.id.nav_profile);}
}
