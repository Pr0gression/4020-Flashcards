package com.example.ultimaterecall.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;

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
