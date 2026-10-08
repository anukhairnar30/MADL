package com.triptales.app;
import android.content.*;import android.os.*;import android.view.*;import android.widget.*;import androidx.fragment.app.Fragment;
public class ProfileFragment extends Fragment{
 public View onCreateView(LayoutInflater i,ViewGroup c,Bundle b){
  View v=i.inflate(R.layout.fragment_profile,c,false); SessionManager s=new SessionManager(requireContext());
  EditText name=v.findViewById(R.id.nameEdit); TextView email=v.findViewById(R.id.emailText); TextView initials=v.findViewById(R.id.profileInitials);
  String currentName=s.getName(); name.setText(currentName); email.setText(s.getEmail());
  initials.setText(currentName.isEmpty()?"T":currentName.substring(0,1).toUpperCase());
  v.findViewById(R.id.cameraProfileButton).setOnClickListener(x->startActivity(new Intent(requireContext(),CameraActivity.class)));
  v.findViewById(R.id.saveProfileButton).setOnClickListener(x->{String newName=name.getText().toString().trim();if(newName.isEmpty()){name.setError("Name is required");return;}s.login(s.getEmail(),newName);initials.setText(newName.substring(0,1).toUpperCase());Toast.makeText(requireContext(),"Profile updated",Toast.LENGTH_SHORT).show();});
  v.findViewById(R.id.logoutButton).setOnClickListener(x->{s.logout();startActivity(new Intent(requireContext(),LoginActivity.class));requireActivity().finish();});
  return v;
 }
}
