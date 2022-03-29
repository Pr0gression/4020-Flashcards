package com.example.ultimaterecall.ui.cards;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.PackObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PackAdapter extends RecyclerView.Adapter<PackAdapter.ViewHolder> {
    private ArrayList<PackObject> PackList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView descTV;
        private final SwitchCompat selectedSwitch;
        private final FloatingActionButton editButton;

        public ViewHolder(View v) {
            super(v);
            nameTV = (TextView) v.findViewById(R.id.idPackName);
            descTV = (TextView) v.findViewById(R.id.idPackDesc);
            selectedSwitch = (SwitchCompat) v.findViewById(R.id.idSelectedSwitch);
            editButton = (FloatingActionButton) v.findViewById(R.id.idEditButton);
        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
        public SwitchCompat getSelectedSwitch() { return selectedSwitch; }
        public FloatingActionButton getEditButton() { return editButton; }
    }

    public PackAdapter(ArrayList<PackObject> list) {
        PackList = list;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pack_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String packName = PackList.get(position).getName();
        String packDesc = PackList.get(position).getDesc();
        viewHolder.getNameTV().setText(packName);
        viewHolder.getDescTV().setText(packDesc);

        SwitchCompat toggle = viewHolder.getSelectedSwitch();
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("Item (" + packName + ") selected");
                } else {
                    System.out.println("Item (" + packName + ") deselected");
                }
            }
        });
        FloatingActionButton editButton = viewHolder.getEditButton();
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Edit pack button not yet implemented...";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(view.getContext(), text, duration);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return PackList.size();
    }
}


