package com.example.ultimaterecall.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    final String FLASHCARDS_PER_DAY = "flashcardsPerDay", FLASHCARDS_GROUP_SIZE = "flashcardGroupSize",
            INTERVAL_GROWTH_FACTOR = "intervalGrowthFactor", PAUSE = "pause";

    private Button addTimePeriodButton;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //createAddTimePeriodButton();
        //createApplySettingsButton();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createAddTimePeriodButton() {
        addTimePeriodButton = (Button)getView().findViewById(R.id.addTimePeriod);
        addTimePeriodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
            }
        });
    }

    private void createApplySettingsButton() {
        addTimePeriodButton = (Button)getView().findViewById(R.id.applyButton);
        addTimePeriodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // click handling code
                SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();

                EditText eFPD = (EditText)getView().findViewById(R.id.flashcardsPerDayInput);
                String fpd = eFPD.getText().toString();
                editor.putInt(FLASHCARDS_PER_DAY, Integer.parseInt(fpd));

                EditText eFGS = (EditText)getView().findViewById(R.id.flashcardGroupSizeInput);
                String fgs = eFGS.getText().toString();
                editor.putInt(FLASHCARDS_GROUP_SIZE, Integer.parseInt(fgs));

                EditText eIGF = (EditText)getView().findViewById(R.id.intervalGrowthFactorInput);
                String igf = eIGF.getText().toString();
                editor.putInt(INTERVAL_GROWTH_FACTOR, Integer.parseInt(igf));

                Switch pauseSwitch = (Switch)getView().findViewById(R.id.pauseSchedulerSwitch);
                boolean pause = pauseSwitch.isChecked();
                editor.putBoolean(PAUSE, pause);
                editor.apply();
            }
        });
    }
}