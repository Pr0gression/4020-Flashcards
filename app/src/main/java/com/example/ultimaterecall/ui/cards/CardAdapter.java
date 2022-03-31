package com.example.ultimaterecall.ui.cards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.MultipleChoiceCard;
import com.example.ultimaterecall.objects.TextCard;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<CardObject> CardList = new ArrayList<>();
    private Button addButton;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTV;
        private final TextView descTV;
        //private final SwitchCompat selectedSwitch;
        private final Button editButton;
        //private final Button addButton;

        public ViewHolder(View v) {
            super(v);
            nameTV = (TextView) v.findViewById(R.id.idCardPrompt);
            descTV = (TextView) v.findViewById(R.id.idCardAnswer);
            //selectedSwitch = (SwitchCompat) v.findViewById(R.id.idSelectedSwitch);
            editButton =  v.findViewById(R.id.idEditButton);
        }
        public TextView getNameTV() {
            return nameTV;
        }
        public TextView getDescTV() { return descTV; }
        //public SwitchCompat getSelectedSwitch() { return selectedSwitch; }
        public Button getEditButton() { return editButton; }
    }

    public CardAdapter(ArrayList<CardObject> list) {
        CardList = list;
    }

    @Override
    @NonNull
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.flashcard_listing, viewGroup, false);

        addButton = (Button) viewGroup.getRootView().findViewById(R.id.idAddCard);
        addButton.setOnClickListener( view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("cardEditNumber",-1);
            Navigation.findNavController(view).navigate(R.id.action_navigation_pack_to_navigation_creator,bundle);
        });

        return new CardAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder viewHolder, final int position) {
        String cardName;
        String cardDesc;
        if (CardList.get(position) instanceof TextCard) {
            cardName = ((TextCard) CardList.get(position)).getPrompt();
            cardDesc = "Answer: " + ((TextCard) CardList.get(position)).getAnswer();
        }
        else {
            cardName = ((MultipleChoiceCard) CardList.get(position)).getPrompt();
            cardDesc = "Possible Answers: " + ((MultipleChoiceCard) CardList.get(position)).getAnswers()[0] + ", " +
                    ((MultipleChoiceCard) CardList.get(position)).getAnswers()[1] + ", " +
                    ((MultipleChoiceCard) CardList.get(position)).getAnswers()[2];
        }
        viewHolder.editButton.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("cardEditNumber",position);
            Navigation.findNavController(view).navigate(R.id.action_navigation_pack_to_navigation_creator,bundle);
        });

        viewHolder.getNameTV().setText(cardName);
        viewHolder.getDescTV().setText(cardDesc);

        //SwitchCompat toggle = viewHolder.getSelectedSwitch();
        /*toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("Item (" + cardName + ") selected");
                } else {
                    System.out.println("Item (" + cardName + ") deselected");
                }
            }
        });*/
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hi from item " + cardName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }
}
