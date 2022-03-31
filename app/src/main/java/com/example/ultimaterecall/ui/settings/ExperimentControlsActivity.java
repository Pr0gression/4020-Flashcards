package com.example.ultimaterecall.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ultimaterecall.MainActivity;
import com.example.ultimaterecall.R;
import com.example.ultimaterecall.data.FakeDatabase;
import com.example.ultimaterecall.objects.IMultipleChoiceCard;
import com.example.ultimaterecall.objects.IPackObject;
import com.example.ultimaterecall.objects.ITextCard;
import com.example.ultimaterecall.objects.TextCard;
import com.example.ultimaterecall.ui.notifications.NotificationDispatcher;
import com.example.ultimaterecall.ui.notifications.NotificationLogger;
import com.example.ultimaterecall.ui.notifications.NotificationScheduler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//Screen for controls related to conducting the experiment
public class ExperimentControlsActivity extends AppCompatActivity
{
    private static final long TIMELY_THRESHOLD = 1000*60*30; //30 minutes
    private static final int START_HOUR = 9;
    private static final int END_HOUR = 17;

    private static final String SHARED_PREFERENCES_KEY = "ExperimentControls_preferences";
    private static final String SCHEDULED_DECK_KEY = "ExperimentControls_scheduledDeck";

    private TextView responseRateText;
    private TextView timelyRateText;

    private Button scheduleButton1;
    private Button scheduleButton2;

    private Button testTextButton;
    private Button testMCButton;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_controls);

        pref = getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);

        responseRateText = (TextView)findViewById(R.id.responseRateText);
        timelyRateText = (TextView)findViewById(R.id.timelyResponseRateText);
        scheduleButton1 = (Button)findViewById(R.id.scheduleDeck1Button);
        scheduleButton2 = (Button)findViewById(R.id.scheduleDeck2Button);
        testTextButton = (Button)findViewById(R.id.sendTestTextButton);
        testMCButton = (Button)findViewById(R.id.sendTestMCButton);

        scheduleButton1.setOnClickListener(v -> {
            schedulePackNextDay(0);
            pref.edit().putInt(SCHEDULED_DECK_KEY, 0).apply();
            updateScheduleButtons();
        });
        scheduleButton2.setOnClickListener(v -> {
            schedulePackNextDay(1);
            pref.edit().putInt(SCHEDULED_DECK_KEY, 1).apply();
            updateScheduleButtons();
        });
        
        testTextButton.setOnClickListener(v -> {
            IPackObject pack = new FakeDatabase().getPacks().get(0);
            int cardIndex = 0;
            while(!(pack.getCard(cardIndex) instanceof ITextCard) && cardIndex < pack.getSize()-1)
                cardIndex++;

            NotificationDispatcher.sendPromptFlashcard(this, 0, cardIndex, false);
        });
        testMCButton.setOnClickListener(v -> {
            IPackObject pack = new FakeDatabase().getPacks().get(0);
            int cardIndex = 0;
            while(!(pack.getCard(cardIndex) instanceof IMultipleChoiceCard) && cardIndex < pack.getSize()-1)
                cardIndex++;

            NotificationDispatcher.sendPromptFlashcard(this, 0, cardIndex, false);
        });
        updateScheduleButtons();
        displayRates();
    }

    private void updateScheduleButtons()
    {
        boolean enable = pref.getInt(SCHEDULED_DECK_KEY, -1) < 0;
        scheduleButton1.setEnabled(enable);
        scheduleButton2.setEnabled(enable);
    }

    private void displayRates()
    {
        int scheduledDeck = pref.getInt(SCHEDULED_DECK_KEY, -1);
        if(scheduledDeck >= 0)
        {
            responseRateText.setText(getResources().getText(R.string.response_rate) + " " + NotificationLogger.timelyResponseRate(this, scheduledDeck, Long.MAX_VALUE));
            timelyRateText.setText(getResources().getText(R.string.timely_response_rate) + " " + NotificationLogger.timelyResponseRate(this, scheduledDeck, TIMELY_THRESHOLD));
        }
        else
        {
            responseRateText.setText(getResources().getText(R.string.response_rate) + " N/A");
            timelyRateText.setText(getResources().getText(R.string.timely_response_rate) + " N/A");
        }
    }

    //Schedule the given pack between the 3 seconds from now and 30 seconds from now
    private void schedulePackQuickTesting(int packIndex)
    {
        List<Date> startTimes = new ArrayList<Date>();
        List<Date> endTimes = new ArrayList<Date>();

        startTimes.add(new Date(System.currentTimeMillis() + 3000));
        endTimes.add(new Date(System.currentTimeMillis() + 30000));

        NotificationScheduler.schedulePack(this, packIndex, startTimes, endTimes);
    }

    //Schedule the given pack for the next day
    private void schedulePackNextDay(int packIndex)
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
        endTimes.add(cal.getTime());

        NotificationScheduler.schedulePack(this, packIndex, startTimes, endTimes);
    }
}