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

//TODO: Implement in-interface way of scheduling pack (for next day?)

//Schedules flashcard notifications for delivery within provide windows
public class NotificationScheduler extends BroadcastReceiver
{
    private static final String PACK_INDEX_LABEL = "packIndex";
    private static final String CARD_INDEX_LABEL = "cardIndex";

    private static final int START_HOUR = 9;
    private static final int END_HOUR = 17;

    public static void schedulePackNextDay(Context context, int packIndex)
    {
        List<Date> startTimes = new ArrayList<Date>();
        List<Date> endTimes = new ArrayList<Date>();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis() + (1000*60*60*24));
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);

        cal.set(Calendar.HOUR_OF_DAY, START_HOUR);
        startTimes.add(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, END_HOUR);
        startTimes.add(cal.getTime());

        schedulePack(context, packIndex, startTimes, endTimes);
    }

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
        ICardObject card = new FakeDatabase().getPacks().get(packIndex).getCard(cardIndex);

        //Send notification
        NotificationDispatcher.sendPromptFlashcard(context, card);
    }
}
