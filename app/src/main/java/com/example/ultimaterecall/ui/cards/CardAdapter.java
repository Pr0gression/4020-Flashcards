package com.example.ultimaterecall.ui.cards;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.CardObject;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<CardObject> CardList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView descTV;
        private final SwitchCompat selectedSwitch;

        public ViewHolder(View v) {
            super(v);
            nameTV = (TextView) v.findViewById(R.id.idPackName);
            descTV = (TextView) v.findViewById(R.id.idPackDesc);
            selectedSwitch = (SwitchCompat) v.findViewById(R.id.idSelectedSwitch);

        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
        public SwitchCompat getSelectedSwitch() { return selectedSwitch; }
    }

    public CardAdapter(ArrayList<CardObject> list) {
        CardList = list;
    }

    @Override
    @NonNull
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pack_card, viewGroup, false);

        return new CardAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, final int position) {
        String packName = "";//CardList.get(position).getName();
        String packDesc = "";//CardList.get(position).getDesc();
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hi from item " + packName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }
}
