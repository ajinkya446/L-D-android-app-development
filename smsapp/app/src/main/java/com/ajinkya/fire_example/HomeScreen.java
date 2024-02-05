package com.ajinkya.fire_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import java.util.Calendar;

public class HomeScreen extends AppCompatActivity {
    Button buttonNotification, buttonMessage, buttonPeriodicNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        buttonMessage = findViewById(R.id.button2);
        buttonNotification = findViewById(R.id.button);
        buttonPeriodicNotifications = findViewById(R.id.button3);
        try {/// Code for periodic Notifications.
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 57);
            calendar.set(Calendar.SECOND, 0);

            Intent calendarIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
            PendingIntent pendingIntent;
            pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, calendarIntent, PendingIntent.FLAG_MUTABLE);
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        } catch (Exception e) {
            throw e;
        }
        buttonMessage.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        buttonNotification.setOnClickListener(v -> {
            NotificationReceiver notificationReceiver = new NotificationReceiver();
            notificationReceiver.onReceive(this, new Intent(this, MainActivity.class));
        });

        buttonPeriodicNotifications.setOnClickListener(v -> {
            Intent firebaseScreenIntent = new Intent(this, SaveDetails.class);
            startActivity(firebaseScreenIntent);
        });

    }
}