package com.example.ultimaterecall.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.PackObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PackAdapter extends RecyclerView.Adapter<PackAdapter.ViewHolder> {
    private final ArrayList<PackObject> PackList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView descTV;
        private final SwitchCompat selectedSwitch;
        private final Button editButton;
        private final Button reviewButton;

        public ViewHolder(View v) {
            super(v);
            nameTV = v.findViewById(R.id.idPackName);
            descTV = v.findViewById(R.id.idPackDesc);
            selectedSwitch = v.findViewById(R.id.idSelectedSwitch);
            editButton =  v.findViewById(R.id.idEditButton);
            reviewButton = v.findViewById(R.id.idReviewButton);
        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
        public SwitchCompat getSelectedSwitch() { return selectedSwitch; }
        public Button getEditButton() { return editButton; }
        public Button getReviewButton() { return reviewButton; }
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
        viewHolder.editButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("packEditNumber",position);
            Navigation.findNavController(view).navigate(R.id.action_navigation_cards_to_packFragment,bundle);
        });
        viewHolder.reviewButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("packNumber",position);
            Navigation.findNavController(view).navigate(R.id.action_navigation_cards_to_reviewFragment,bundle);
        });

        SwitchCompat toggle = viewHolder.getSelectedSwitch();
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                System.out.println("Item (" + packName + ") selected");
            } else {
                System.out.println("Item (" + packName + ") deselected");
            }
        });
    }

    @Override
    public int getItemCount() {
        return PackList.size();
    }
}


