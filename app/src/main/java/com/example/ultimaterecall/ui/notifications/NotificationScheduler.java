package com.example.ultimaterecall.ui.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.objects.ICardObject;
import com.example.ultimaterecall.objects.IPackObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Schedules flashcard notifications for delivery within provide windows
public class NotificationScheduler extends BroadcastReceiver
{
    private static final String PACK_INDEX_LABEL = "packIndex";
    private static final String CARD_INDEX_LABEL = "cardIndex";

    public static void schedulePack(Context context, int packIndex, List<Date> startTimes, List<Date> endTimes)
    {
        if(startTimes.size() != endTimes.size())
            throw new IllegalArgumentException("Number of start times must match number of end times");

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        long totalTime = 0;
        for(int i = 0; i < startTimes.size(); i++)
            totalTime += endTimes.get(i).getTime() - startTimes.get(i).getTime();

        IPackObject pack = new FakeDatabase().getPacks().get(packIndex);
        int packSize = pack.getSize();

        //Calculate time between notifications
        long interval = totalTime / packSize;

        //Set alarms for notifications
        int currTimeWindow = 0;
        long nextTime = startTimes.get(currTimeWindow).getTime();
        for(int i = 0; i < packSize; i++)
        {
            //Create intent for particular card
            Intent intent = new Intent(context, NotificationScheduler.class);
            intent.putExtra(PACK_INDEX_LABEL, packIndex);
            intent.putExtra(CARD_INDEX_LABEL, i);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            //Schedule at nextTime
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pIntent);

            //Compute next time
            nextTime += interval;
            if(nextTime > endTimes.get(currTimeWindow).getTime())
            {
                currTimeWindow++;
                nextTime = startTimes.get(currTimeWindow).getTime();
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //Obtain card
        int packIndex = intent.getIntExtra(PACK_INDEX_LABEL, -1);
        int cardIndex = intent.getIntExtra(CARD_INDEX_LABEL, -1);

        //Send notification
        NotificationDispatcher.sendPromptFlashcard(context, packIndex, cardIndex);
    }
}
