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

//Sends out flashcard notifications
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

        int notificationID = (int)System.currentTimeMillis();

        //TODO: Add log when flashcard dispatched

        notificationManager.notify(notificationID, buildPromptNotification(context, card, notificationID));
    }

    //Send a notification providing the given answer for a card
    public static void sendAnswerNotification(Context context, String answer)
    {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Create notification channel if new enough OS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT));

        //TODO: Reconsider icon, title
        //Create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        builder.setContentTitle(context.getResources().getString(R.string.answer_notification_title));
        builder.setAutoCancel(false);
        builder.setContentText(answer);

        notificationManager.notify((int)System.currentTimeMillis(), builder.build());

        //TODO: Add log when flashcard answered
    }

    //Build the prompt notification for the given card
    private static Notification buildPromptNotification(Context context, ICardObject card, int notificationID)
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
        builder.setContentTitle(context.getResources().getString(R.string.prompt_notification_title));
        builder.setContentIntent(pInappIntent);
        builder.setAutoCancel(false);

        //Add card content based on type
        if(card instanceof ITextCard)
            addTextContent(context, (ITextCard)card, builder, notificationID);
        else if(card instanceof IMultipleChoiceCard)
            addMultipleChoiceContent(context, (IMultipleChoiceCard)card, builder, notificationID);

        return builder.build();
    }

    private static void addTextContent(Context context, ITextCard card, NotificationCompat.Builder builder, int notificationID)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add reveal action
        Intent revealIntent = new Intent(context, NotificationAnswerer.class);
        revealIntent.putExtra(NotificationAnswerer.ANSWER_LABEL, card.getAnswer());
        revealIntent.putExtra(NotificationAnswerer.NOTIFICATION_ID_LABEL, notificationID);
        PendingIntent pRevealIntent = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), revealIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //TODO: Implement reveal intent (second notification), also intent must dismiss notification

        NotificationCompat.Action reveal = new NotificationCompat.Action(0, "Reveal", pRevealIntent);
        builder.addAction(reveal);
    }

    private static void addMultipleChoiceContent(Context context, IMultipleChoiceCard card, NotificationCompat.Builder builder, int notificationID)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add multiple choice actions
        //TODO: Implement action intents (second notification), should say correct or wrong, also intent must dismiss notification
        for(String answerText : card.getAnswers())
        {
            String answerResponse;
            if(answerText.equals(card.getAnswers()[card.getAnswerIndex()]))
                answerResponse = context.getResources().getString(R.string.answer_notification_mchoice_correct);
            else
                answerResponse = context.getResources().getString(R.string.answer_notification_mchoice_wrong) + " " + card.getAnswers()[card.getAnswerIndex()];

            Intent answerIntent = new Intent(context, NotificationAnswerer.class);
            answerIntent.putExtra(NotificationAnswerer.ANSWER_LABEL, answerResponse);
            answerIntent.putExtra(NotificationAnswerer.NOTIFICATION_ID_LABEL, notificationID);
            PendingIntent pAnswerIntent = PendingIntent.getBroadcast(context, (int)System.currentTimeMillis(), answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Action answer = new NotificationCompat.Action(0, answerText, pAnswerIntent);
            builder.addAction(answer);
        }
    }
}
