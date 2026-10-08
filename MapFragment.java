package com.triptales.app;
import android.Manifest;import android.content.pm.PackageManager;import android.os.*;import android.view.*;import androidx.annotation.NonNull;import androidx.core.app.ActivityCompat;import androidx.fragment.app.Fragment;import com.google.android.gms.location.*;import com.google.android.gms.maps.*;import com.google.android.gms.maps.model.*;
public class MapFragment extends Fragment implements OnMapReadyCallback{
 GoogleMap map;static final int LOC=81;
 public View onCreateView(LayoutInflater i,ViewGroup c,Bundle b){return i.inflate(R.layout.fragment_map,c,false);}
 public void onViewCreated(@NonNull View v,Bundle b){super.onViewCreated(v,b);SupportMapFragment m=SupportMapFragment.newInstance();getChildFragmentManager().beginTransaction().replace(R.id.mapContainer,m).commit();m.getMapAsync(this);}
 public void onMapReady(GoogleMap g){map=g;map.getUiSettings().setZoomControlsEnabled(true);if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){map.setMyLocationEnabled(true);center();}else ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOC);}
 void center(){FusedLocationProviderClient c=LocationServices.getFusedLocationProviderClient(requireActivity());c.getLastLocation().addOnSuccessListener(l->{if(l!=null){LatLng p=new LatLng(l.getLatitude(),l.getLongitude());map.addMarker(new MarkerOptions().position(p).title("You are here"));map.animateCamera(CameraUpdateFactory.newLatLngZoom(p,13));}});}
 public void onRequestPermissionsResult(int r,@NonNull String[]p,@NonNull int[]g){super.onRequestPermissionsResult(r,p,g);if(r==LOC&&g.length>0&&g[0]==PackageManager.PERMISSION_GRANTED&&map!=null){if(ActivityCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){map.setMyLocationEnabled(true);center();}}}
}
