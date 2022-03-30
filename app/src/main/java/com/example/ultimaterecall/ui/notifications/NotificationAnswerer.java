package com.example.ultimaterecall.ui.notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//Waits for flashcard notifications to be answered, and responds to them with answer notifications
public class NotificationAnswerer extends BroadcastReceiver
{
    public static final String ANSWER_LABEL = "answer";
    public static final String NOTIFICATION_ID_LABEL = "notificationID";

    public void onReceive(Context context, Intent intent)
    {
        String answer = intent.getStringExtra(ANSWER_LABEL);
        int notificationID = intent.getIntExtra(NOTIFICATION_ID_LABEL, 0);

        ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).cancel(notificationID);
        NotificationDispatcher.sendAnswerNotification(context, answer);
    }
}
