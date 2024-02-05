package com.ajinkya.fire_example;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "local", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 100, intent, PendingIntent.FLAG_MUTABLE);

            Intent dismissIntent = new Intent(context, DismissReceiver.class);
            PendingIntent dismissPendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 100, dismissIntent, PendingIntent.FLAG_MUTABLE);

            Notification.Action action = new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.baseline_add_alert_24), "Send Message", pendingIntent).build();
            Notification.Action dismissIntentAction = new Notification.Action.Builder(Icon.createWithResource(context, R.drawable.baseline_add_alert_24), "dismiss", dismissPendingIntent).build();

            Notification.Builder builder = new Notification.Builder(context, "1");

            builder.setSmallIcon(R.drawable.baseline_add_alert_24).addAction(action).addAction(dismissIntentAction).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle("Test Notification").setContentText("context is the test local notifications");

            NotificationManagerCompat compat = NotificationManagerCompat.from(context);
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            compat.notify(1, builder.build());
        }
    }
}
