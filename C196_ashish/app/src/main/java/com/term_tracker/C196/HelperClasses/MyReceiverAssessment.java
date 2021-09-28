package com.term_tracker.C196.HelperClasses;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.term_tracker.scheduler.R;

import androidx.core.app.NotificationCompat;

public class MyReceiverAssessment extends BroadcastReceiver {

    private String channel_id = "test";
    private static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context, channel_id);
        Notification myNotification = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.schoolnotification)
                .setContentTitle("Important Assessment Notification")
                .setContentText(intent.getStringExtra("assessmentTitle") + " is due today!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, myNotification);
    }




    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getResources().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}