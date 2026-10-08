package com.triptales.app;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {
    public static final String ACTION_PLAY = "com.triptales.app.PLAY";
    public static final String ACTION_PAUSE = "com.triptales.app.PAUSE";
    public static final String ACTION_STOP = "com.triptales.app.STOP";
    private static final String CHANNEL = "triptales_music";
    private MediaPlayer player;

    @Override public void onCreate() {
        super.onCreate();
        createChannel();
        player = MediaPlayer.create(this, R.raw.triptales_ambient);
        if (player != null) {
            player.setLooping(true);
        }
        startForeground(202, buildNotification("Travel soundtrack ready"));
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent == null ? ACTION_PLAY : intent.getAction();
        if (ACTION_PAUSE.equals(action)) {
            if (player != null && player.isPlaying()) player.pause();
            updateNotification("Music paused");
        } else if (ACTION_STOP.equals(action)) {
            stopSelf();
        } else {
            if (player != null && !player.isPlaying()) player.start();
            updateNotification("Playing TripTales soundtrack");
        }
        return START_NOT_STICKY;
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL, "TripTales Music", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("TripTales travel soundtrack controls");
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
        }
    }

    private Notification buildNotification(String text) {
        return new NotificationCompat.Builder(this, CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("TripTales Music")
                .setContentText(text)
                .setOngoing(true)
                .build();
    }

    private void updateNotification(String text) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(202, buildNotification(text));
    }

    @Override public void onDestroy() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        super.onDestroy();
    }

    @Override public IBinder onBind(Intent intent) { return null; }
}
