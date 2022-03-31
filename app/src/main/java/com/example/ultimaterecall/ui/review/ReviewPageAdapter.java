package com.example.ultimaterecall.ui.review;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.MultipleChoiceCard;
import com.example.ultimaterecall.objects.TextCard;

import java.util.ArrayList;


class ReviewPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int[] cardType;
    private final Context context;
    private ArrayList<CardObject> cards;
    boolean correct = false;


    ReviewPageAdapter(Context context, ArrayList<CardObject> cards) {
        this.context = context;
        this.cards = cards;
        cardType = new int[cards.size()];
        for (int i=0;i<cards.size();i++){
            if (cards.get(i) instanceof TextCard) {
                cardType[i] = 0;
            } else if (cards.get(i) instanceof MultipleChoiceCard) {
                cardType[i] = 1;
            } else {
                cardType[i] = 2;
            }
        }
    }

    public void setDone() {
        System.out.println("Done");

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        switch (holder.getItemViewType()) {
            case 0: // Text
                TViewHolder textCardHolder = (TViewHolder) holder;
                textCardHolder.answerText.setVisibility(View.INVISIBLE);

                break;
            case 1: // Multiple Choice
                MCViewHolder multipleCardHolder = (MCViewHolder) holder;
                multipleCardHolder.answerText1.setVisibility(View.VISIBLE);
                multipleCardHolder.answerText1.setBackgroundResource(R.drawable.thick_border);
                multipleCardHolder.answerText2.setVisibility(View.VISIBLE);
                multipleCardHolder.answerText2.setBackgroundResource(R.drawable.thick_border);
                multipleCardHolder.answerText3.setVisibility(View.VISIBLE);
                multipleCardHolder.answerText3.setBackgroundResource(R.drawable.thick_border);
                break;
            default: // Image
        }
    }

    @Override
    public int getItemViewType(int position) {
        return cardType[position];
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(context).inflate(R.layout.text_card_review_page, parent, false);
        View multipleView = LayoutInflater.from(context).inflate(R.layout.multiple_choice_card_review_page, parent, false);
        View imageView = LayoutInflater.from(context).inflate(R.layout.multiple_choice_card_review_page, parent, false);
        switch (viewType) {
            case 0: // Text Card
                return new TViewHolder(textView);
            case 1: // Multiple Choice Card
                return new MCViewHolder(multipleView);
            default: // Image Card
                return new ImageViewHolder(imageView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0: // Text
                TViewHolder textCardHolder = (TViewHolder) holder;
                TextCard tc = (TextCard)cards.get(position);
                textCardHolder.promptText.setText(tc.getPrompt());
                textCardHolder.answerText.setText(tc.getAnswer());
                break;
            case 1: // Multiple Choice
                MCViewHolder multipleCardHolder = (MCViewHolder) holder;
                MultipleChoiceCard mc = (MultipleChoiceCard)cards.get(position);
                multipleCardHolder.promptText.setText(mc.getPrompt());
                multipleCardHolder.answerText1.setText(mc.getAnswers()[0]);
                multipleCardHolder.answerText2.setText(mc.getAnswers()[1]);
                multipleCardHolder.answerText3.setText(mc.getAnswers()[2]);
                multipleCardHolder.revealButton.setOnClickListener( v-> {
                    switch( mc.getAnswerIndex() ) {
                        case 0:
                            multipleCardHolder.answerText2.setVisibility(View.INVISIBLE);
                            multipleCardHolder.answerText3.setVisibility(View.INVISIBLE);
                            break;
                        case 1:
                            multipleCardHolder.answerText1.setVisibility(View.INVISIBLE);
                            multipleCardHolder.answerText3.setVisibility(View.INVISIBLE);
                            break;
                        default:
                            multipleCardHolder.answerText1.setVisibility(View.INVISIBLE);
                            multipleCardHolder.answerText2.setVisibility(View.INVISIBLE);
                    }
                });
                multipleCardHolder.answerText1.setOnClickListener(view -> {
                    if (mc.getAnswerIndex() == 0) {
                        correct = true;
                        System.out.println("Correct!");
                        multipleCardHolder.answerText1.setBackgroundResource(R.drawable.thick_border_selected);
                    }
                });
                multipleCardHolder.answerText2.setOnClickListener(view -> {
                    if (mc.getAnswerIndex() == 1) {
                        correct = true;
                        System.out.println("Correct!");
                        multipleCardHolder.answerText2.setBackgroundResource(R.drawable.thick_border_selected);
                    }
                });
                multipleCardHolder.answerText3.setOnClickListener(view -> {
                    if (mc.getAnswerIndex() == 2) {
                        correct = true;
                        System.out.println("Correct!");
                        multipleCardHolder.answerText3.setBackgroundResource(R.drawable.thick_border_selected);
                    }
                });
                break;
            default: // Image
        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class TViewHolder extends RecyclerView.ViewHolder {
        TextView promptText;
        TextView answerText;
        Button revealButton;

        public TViewHolder(View itemView) {
            super(itemView);
            promptText = itemView.findViewById(R.id.idTextCardPrompt);
            answerText = itemView.findViewById(R.id.idTextCardAnswer);
            revealButton = itemView.findViewById(R.id.idTextRevealButton);
            revealButton.setOnClickListener( v-> answerText.setVisibility(View.VISIBLE));

        }
    }

    public static class MCViewHolder extends RecyclerView.ViewHolder {
        TextView promptText;
        TextView answerText1;
        TextView answerText2;
        TextView answerText3;
        Button revealButton;

        public MCViewHolder(View itemView) {
            super(itemView);
            promptText = itemView.findViewById(R.id.idMultipleCardPrompt);
            answerText1 = itemView.findViewById(R.id.idMultipleAnswer1);
            answerText2 = itemView.findViewById(R.id.idMultipleAnswer2);
            answerText3 = itemView.findViewById(R.id.idMultipleAnswer3);
            revealButton = itemView.findViewById(R.id.idMultipleRevealButton);
        }
    }

    // To be implemented
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView promptImage;
        ImageView answerImage;
        Button revealButton;

        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
