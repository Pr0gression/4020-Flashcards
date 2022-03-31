package com.example.ultimaterecall.ui.creation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.ui.cards.CardAdapter;

public class CreatorAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

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

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, final int position) {

    }

    @Override
    @NonNull
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;//CardList.size();
    }
}
