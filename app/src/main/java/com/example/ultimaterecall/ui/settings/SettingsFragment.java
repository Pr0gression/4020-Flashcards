package com.example.ultimaterecall.ui.settings;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    private Button addTimePeriodButton;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createAddTimePeriodButton();
        addAvailability();

        ((Button)root.findViewById(R.id.experimentControlsButton)).setOnClickListener(v -> {
            //
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addAvailability() {
        LinearLayout layout = (LinearLayout) binding.getRoot().findViewById(R.id.availabilityLinearLayout);
        LinearLayout newAvailability = new LinearLayout(getActivity());
        TextView from = new TextView(getActivity());
        TextView to = new TextView(getActivity());
        EditText fromEdit = new EditText(getActivity());
        EditText toEdit = new EditText(getActivity());

        Button deleteButton = new Button(getActivity());

        from.setText("From: ");
        to.setText("To: ");

        fromEdit.setInputType(InputType.TYPE_CLASS_DATETIME|InputType.TYPE_DATETIME_VARIATION_TIME);
        toEdit.setInputType(InputType.TYPE_CLASS_DATETIME|InputType.TYPE_DATETIME_VARIATION_TIME);

        deleteButton.setText("X");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // click handling code
                LinearLayout layout = (LinearLayout) binding.getRoot().findViewById(R.id.availabilityLinearLayout);
                LinearLayout deleteButtonParent = (LinearLayout) deleteButton.getParent();

                layout.removeView(deleteButtonParent);
            }
        });

        newAvailability.setOrientation(LinearLayout.HORIZONTAL);
        newAvailability.addView(from);
        newAvailability.addView(fromEdit);
        newAvailability.addView(to);
        newAvailability.addView(toEdit);
        newAvailability.addView(deleteButton);

        layout.addView(newAvailability);
    }

    private void createAddTimePeriodButton() {
        if(addTimePeriodButton == null) {
            addTimePeriodButton = (Button) binding.getRoot().findViewById(R.id.addTimePeriodButton);
            addTimePeriodButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // click handling code
                    addAvailability();
                }
            });
        }
    }
}