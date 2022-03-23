package com.example.ultimaterecall.ui.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.PackObject;

import java.util.ArrayList;

public class PackAdapter extends RecyclerView.Adapter<PackAdapter.ViewHolder> {
    private ArrayList<PackObject> PackList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView descTV;
        private final Switch selectedSwitch;

        public ViewHolder(View v) {
            super(v);
            nameTV = (TextView) v.findViewById(R.id.idPackName);
            descTV = (TextView) v.findViewById(R.id.idPackDesc);
            selectedSwitch = (Switch) v.findViewById(R.id.idSelectedSwitch);

        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
        public Switch getSelectedSwitch() { return selectedSwitch; }
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

        Switch toggle = viewHolder.getSelectedSwitch();
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("Item " + packName + " selected");
                } else {
                    System.out.println("Item " + packName + " deselected");
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
        return PackList.size();
    }
}


