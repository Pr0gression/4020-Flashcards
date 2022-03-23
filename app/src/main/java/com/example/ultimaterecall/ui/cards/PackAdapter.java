package com.example.ultimaterecall.ui.cards;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public ViewHolder(View v) {
            super(v);
            nameTV = (TextView) v.findViewById(R.id.idPackName);
            descTV = (TextView) v.findViewById(R.id.idPackDesc);

        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
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
        viewHolder.getNameTV().setText(PackList.get(position).getName());
        viewHolder.getDescTV().setText(PackList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return PackList.size();
    }
}


