package com.example.ultimaterecall.ui.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.ultimaterecall.MainActivity;
import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.objects.ICardObject;
import com.example.ultimaterecall.objects.IMultipleChoiceCard;
import com.example.ultimaterecall.objects.ITextCard;

//Sends out flashcard notifications
public class NotificationDispatcher
{
    private static final String NOTIFICATION_CHANNEL_ID = "flashcardNotifications";
    private static final String NOTIFICATION_CHANNEL_NAME = "Flashcard Notifications";

    private static final String SHARED_PREFERENCES_KEY = "NotificationDispatcher_preferences";
    private static final String NEXT_ID_PREFERENCE_KEY = "NotificationDispatcher_nextID";

    //Send a notification for the given card
    public static void sendPromptFlashcard(Context context, int packIndex, int cardIndex)
    {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Create notification channel if new enough OS
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT));

        int notificationID = getNextID(context);

        //TODO: Add log when flashcard dispatched
        NotificationLogger.log(context, packIndex, cardIndex, System.currentTimeMillis(), NotificationLogger.LogType.DISPATCH);

        notificationManager.notify(notificationID, buildPromptNotification(context, packIndex, cardIndex, notificationID));
    }

    //Send a notification providing the given answer for a card
    public static void sendAnswerNotification(Context context, int packIndex, int cardIndex, String answer)
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

        notificationManager.notify(getNextID(context), builder.build());

        //TODO: Add log when flashcard answered
        NotificationLogger.log(context, packIndex, cardIndex, System.currentTimeMillis(), NotificationLogger.LogType.RESPONSE);
    }

    //Build the prompt notification for the given card
    private static Notification buildPromptNotification(Context context, int packIndex, int cardIndex, int notificationID)
    {
        //TODO: Reconsider icon, title
        //Initialize general notification data
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        builder.setContentTitle(context.getResources().getString(R.string.prompt_notification_title));
        builder.setAutoCancel(false);

        ICardObject card = new FakeDatabase().getPacks().get(packIndex).getCard(cardIndex);

        //Add card content based on type
        if(card instanceof ITextCard)
            addTextContent(context, packIndex, cardIndex, (ITextCard)card, builder, notificationID);
        else if(card instanceof IMultipleChoiceCard)
            addMultipleChoiceContent(context, packIndex, cardIndex, (IMultipleChoiceCard)card, builder, notificationID);

        return builder.build();
    }

    private static void addTextContent(Context context, int packIndex, int cardIndex, ITextCard card, NotificationCompat.Builder builder, int notificationID)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add reveal action
        Intent revealIntent = new Intent(context, NotificationAnswerer.class);
        revealIntent.putExtra(NotificationAnswerer.ANSWER_LABEL, card.getAnswer());
        revealIntent.putExtra(NotificationAnswerer.NOTIFICATION_ID_LABEL, notificationID);
        revealIntent.putExtra(NotificationAnswerer.PACK_INDEX_LABEL, packIndex);
        revealIntent.putExtra(NotificationAnswerer.CARD_INDEX_LABEL, cardIndex);
        PendingIntent pRevealIntent = PendingIntent.getBroadcast(context, getNextID(context), revealIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action reveal = new NotificationCompat.Action(0, "Reveal", pRevealIntent);
        builder.addAction(reveal);
    }

    private static void addMultipleChoiceContent(Context context, int packIndex, int cardIndex, IMultipleChoiceCard card, NotificationCompat.Builder builder, int notificationID)
    {
        //Add prompt text
        builder.setContentText(card.getPrompt());

        //Add multiple choice actions
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
            answerIntent.putExtra(NotificationAnswerer.PACK_INDEX_LABEL, packIndex);
            answerIntent.putExtra(NotificationAnswerer.CARD_INDEX_LABEL, cardIndex);
            PendingIntent pAnswerIntent = PendingIntent.getBroadcast(context, getNextID(context), answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Action answer = new NotificationCompat.Action(0, answerText, pAnswerIntent);
            builder.addAction(answer);
        }
    }

    private static int getNextID(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        int id = preferences.getInt(NEXT_ID_PREFERENCE_KEY, 0);
        preferences.edit().putInt(NEXT_ID_PREFERENCE_KEY, id+1).apply();

        return id;
    }
}
