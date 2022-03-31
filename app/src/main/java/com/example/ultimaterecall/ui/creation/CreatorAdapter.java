package com.example.ultimaterecall.ui.creation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.ui.cards.CardAdapter;

public class CreatorAdapter extends RecyclerView.Adapter<CreatorAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);

        }
    }

    @Override
    @NonNull
    public CreatorAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_creation_page, viewGroup, false);

        return new CreatorAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CreatorAdapter.ViewHolder viewHolder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 0;//CardList.size();
    }
}
