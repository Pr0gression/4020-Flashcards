package com.example.ultimaterecall.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.objects.IPackObject;

//Saves records of the dispatching of and response to flashcard notifications
public class NotificationLogger
{
    public enum LogType { DISPATCH, RESPONSE };

    private static final String SHARED_PREFERENCES_KEY = "NotificationLogger_preferences";
    private static final String CARD_LOG_KEY_PREFIX = "NotificationLogger_";
    private static final String CARD_LOG_KEY_INFIX = ",";
    private static final String CARD_LOG_DISPATCH_KEY_SUFFIX = "_DISPATCH";
    private static final String CARD_LOG_RESPONSE_KEY_SUFFIX = "_RESPONSE";

    //Log the time of dispatch or response for a given card
    public static void log(Context context, int packIndex, int cardIndex, long time, LogType logType)
    {
        SharedPreferences pref = getPreferences(context);

        String key = formCardKey(packIndex, cardIndex, logType);

        //Ensure not already set
        if(pref.contains(key))
            throw new IllegalArgumentException("Card (" + packIndex + "," + cardIndex
                    + ") already logged (" + logType.name() + ")");

        pref.edit().putLong(key, time).apply();
    }

    //Returns the time (in milliseconds) between, -1 if dispatched but never responded to
    public static long responseTime(Context context, int packIndex, int cardIndex)
    {
        SharedPreferences pref = getPreferences(context);

        String dKey = formCardKey(packIndex, cardIndex, LogType.DISPATCH);
        String rKey = formCardKey(packIndex, cardIndex, LogType.RESPONSE);

        if(!pref.contains(dKey))
            throw new IllegalArgumentException("Card (" + packIndex + "," + cardIndex +") never dispatched");

        if(!pref.contains(rKey))
            return -1;

        return pref.getLong(rKey, 0) - pref.getLong(dKey, 0);
    }

    //Returns the portion of dispatched cards in a deck that were responded to in a timely manner
    //timelyThreshold defines the maximum number of milliseconds to be considered timely
    public static double timelyResponseRate(Context context, int packIndex, long timelyThreshold)
    {
        SharedPreferences pref = getPreferences(context);
        IPackObject pack = new FakeDatabase().getPacks().get(packIndex);

        int timelyCount = 0;
        int totalCount = 0;
        for(int cardIndex = 0; cardIndex < pack.getSize(); cardIndex++)
        {
            String dKey = formCardKey(packIndex, cardIndex, LogType.DISPATCH);
            if(pref.contains(formCardKey(packIndex, cardIndex, LogType.DISPATCH)))
            {
                totalCount++;
                long responseTime = responseTime(context, packIndex, cardIndex);

                if(responseTime >= 0 && responseTime < timelyThreshold)
                    timelyCount++;
            }
        }

        if(totalCount == 0)
            return 1.0;

        return ((double)timelyCount) / ((double)totalCount);
    }

    private static SharedPreferences getPreferences(Context context)
    {
        return context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    //Returns the key for the time of dispatch or response for a given card
    //Returns key for response if 'response' is true, dispatch otherwise
    private static String formCardKey(int packIndex, int cardIndex, LogType logType)
    {
        String suffix = "";
        if(logType == LogType.DISPATCH)
            suffix = CARD_LOG_DISPATCH_KEY_SUFFIX;
        else if(logType == LogType.RESPONSE)
            suffix = CARD_LOG_RESPONSE_KEY_SUFFIX;

        return CARD_LOG_KEY_PREFIX + packIndex + CARD_LOG_KEY_INFIX + cardIndex + suffix;
    }
}
