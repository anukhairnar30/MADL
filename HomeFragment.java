package com.triptales.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        SessionManager session = new SessionManager(requireContext());
        String name = session.getName();
        TextView welcome = v.findViewById(R.id.welcomeText);
        welcome.setText("Hi, " + (name == null || name.isEmpty() ? "Traveler" : name) + " 👋");

        TextView avatar = v.findViewById(R.id.homeAvatar);
        String initialSource = (name == null || name.isEmpty()) ? "T" : name.trim();
        avatar.setText(initialSource.substring(0, 1).toUpperCase());

        v.findViewById(R.id.addTripHome).setOnClickListener(x ->
                startActivity(new Intent(requireContext(), AddTripActivity.class)));

        TextView musicStatus = v.findViewById(R.id.musicStatusText);
        v.findViewById(R.id.musicPlayButton).setOnClickListener(x -> {
            requireContext().startForegroundService(
                    new Intent(requireContext(), MusicService.class).setAction(MusicService.ACTION_PLAY));
            musicStatus.setText("Now playing • TripTales travel soundtrack");
            Toast.makeText(requireContext(), "Music started", Toast.LENGTH_SHORT).show();
        });

        v.findViewById(R.id.musicPauseButton).setOnClickListener(x -> {
            requireContext().startService(
                    new Intent(requireContext(), MusicService.class).setAction(MusicService.ACTION_PAUSE));
            musicStatus.setText("Paused • Your soundtrack is waiting");
        });

        v.findViewById(R.id.musicStopButton).setOnClickListener(x -> {
            requireContext().startService(
                    new Intent(requireContext(), MusicService.class).setAction(MusicService.ACTION_STOP));
            musicStatus.setText("Stopped • Ready to play again");
        });

        v.findViewById(R.id.profileHomeCard).setOnClickListener(x ->
                ((MainActivity) requireActivity()).selectProfile());

        return v;
    }
}
