package com.example.ultimaterecall.ui.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.ultimaterecall.MainActivity;
import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.ICardObject;
import com.example.ultimaterecall.objects.IMultipleChoiceCard;
import com.example.ultimaterecall.objects.ITextCard;

public class NotificationDispatcher
{
    private static final String NOTIFICATION_CHANNEL_ID = "flashcardNotifications";
    private static final String NOTIFICATION_CHANNEL_NAME = "Flashcard Notifications";

    //Send a notification for the given card
    public static void sendPromptFlashcard(Context context, ICardObject card)
    {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Create notification channel if new enough OS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT));

        notificationManager.notify((int)System.currentTimeMillis(), buildPromptNotification(context, card));
    }

    //Send a notification providing the answer for the card
    public static void sendAnswerNotification(Context context, String text)
    {
        //TODO: Implement answer notification
    }

    //Build the prompt notification for the given card
    private static Notification buildPromptNotification(Context context, ICardObject card)
    {
        //Create the intent to launch the app to review the card in-app
        //TODO: Currently just launches app, replace with intent to launch into review, also dismiss notification
        Intent inappIntent = new Intent(context, MainActivity.class);
        inappIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pInappIntent = PendingIntent.getActivity(context, 0, inappIntent, 0);

        //TODO: Reconsider icon, title
        //Initialize general notification data
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        builder.setContentTitle(context.getResources().getString(R.string.notification_title));
        builder.setContentIntent(pInappIntent);
        builder.setAutoCancel(false);

        //Add card content based on type
        if(card instanceof ITextCard)
            addTextContent(context, (ITextCard)card, builder);
        else if(card instanceof IMultipleChoiceCard)
            addMultipleChoiceContent(context, (IMultipleChoiceCard)card, builder);

        //TODO: Add log when flashcard answered

        return builder.build();
    }

    private static void addTextContent(Context context, ITextCard card, NotificationCompat.Builder builder)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add reveal action
        //TODO: Implement reveal intent (second notification), also intent must dismiss notification
        Intent revealIntent = null;
        //TODO: pIntent causes crash
        //PendingIntent pRevealIntent = PendingIntent.getActivity(context, 0, revealIntent, 0);

        //NotificationCompat.Action reveal = new NotificationCompat.Action(0, "Reveal", pRevealIntent);
        //builder.addAction(reveal);
    }

    private static void addMultipleChoiceContent(Context context, IMultipleChoiceCard card, NotificationCompat.Builder builder)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add multiple choice actions
        //TODO: Implement action intents (second notification), should say correct or wrong, also intent must dismiss notification
        for(String answerText : card.getAnswers())
        {
            if(answerText.equals(card.getAnswers()[card.getAnswerIndex()]))
            {
                //Do something if correct
            }

            Intent answerIntent = null;
            //TODO: pIntent causes crash
            //PendingIntent pAnswerIntent = PendingIntent.getActivity(context, 0, answerIntent, 0);

            //NotificationCompat.Action answer = new NotificationCompat.Action(0, answerText, pAnswerIntent);
            //builder.addAction(answer);
        }
    }
}
