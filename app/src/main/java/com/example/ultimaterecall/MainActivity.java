package com.example.ultimaterecall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.ultimaterecall.data.DatabaseViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ultimaterecall.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final String FLASHCARDS_PER_DAY = "flashcardsPerDay", FLASHCARDS_GROUP_SIZE = "flashcardGroupSize",
            INTERVAL_GROWTH_FACTOR = "intervalGrowthFactor", PAUSE = "pause";

    private SharedPreferences sharedPreferences;

    private ActivityMainBinding binding;
    private DatabaseViewModel viewModel;
    private Button addTimePeriodButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_cards, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        createAddTimePeriodButton();
        createApplySettingsButton();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    private void createAddTimePeriodButton() {
        addTimePeriodButton = (Button)findViewById(R.id.addTimePeriod);
        addTimePeriodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
            }
        });
    }

    private void createApplySettingsButton() {
        addTimePeriodButton = (Button)findViewById(R.id.applyButton);
        addTimePeriodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                SharedPreferences.Editor editor = sharedPreferences.edit();

                EditText eFPD = (EditText)findViewById(R.id.flashcardsPerDayInput);
                String fpd = eFPD.getText().toString();
                editor.putInt(FLASHCARDS_PER_DAY, Integer.parseInt(fpd));

                EditText eFGS = (EditText)findViewById(R.id.flashcardGroupSizeInput);
                String fgs = eFGS.getText().toString();
                editor.putInt(FLASHCARDS_GROUP_SIZE, Integer.parseInt(fgs));

                EditText eIGF = (EditText)findViewById(R.id.intervalGrowthFactorInput);
                String igf = eIGF.getText().toString();
                editor.putInt(INTERVAL_GROWTH_FACTOR, Integer.parseInt(igf));

                Switch pauseSwitch = (Switch)findViewById(R.id.pauseSchedulerSwitch);
                boolean pause = pauseSwitch.isChecked();
                editor.putBoolean(PAUSE, pause);
                editor.apply();
            }
        });
    }
}