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
    public static final String PACK_INDEX_LABEL = "packIndex";
    public static final String CARD_INDEX_LABEL = "cardIndex";
    public static final String LOG_LABEL = "log";

    public void onReceive(Context context, Intent intent)
    {
        String answer = intent.getStringExtra(ANSWER_LABEL);
        int notificationID = intent.getIntExtra(NOTIFICATION_ID_LABEL, 0);
        int packIndex = intent.getIntExtra(PACK_INDEX_LABEL, 0);
        int cardIndex = intent.getIntExtra(CARD_INDEX_LABEL, 0);
        boolean log = intent.getBooleanExtra(LOG_LABEL, false);

        ((NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE)).cancel(notificationID);
        NotificationDispatcher.sendAnswerNotification(context, packIndex, cardIndex, answer, log);
    }
}
