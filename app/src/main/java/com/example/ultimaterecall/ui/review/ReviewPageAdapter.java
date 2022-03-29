package com.example.ultimaterecall.ui.review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimaterecall.R;
import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.TextCard;


class ReviewPageAdapter extends RecyclerView.Adapter<ReviewPageAdapter.ViewHolder> {

    private String[] prompts;
    private String[] answers;
    private Context context;

    ReviewPageAdapter(Context context, CardObject[] cards) {
        this.context = context;
        prompts = new String[cards.length];
        answers = new String[cards.length];
        for (int i=0;i<cards.length;i++){
            if (cards[i] instanceof TextCard) {
                TextCard tc= (TextCard) cards[i];
                prompts[i] = tc.getPrompt();
                answers[i] = tc.getAnswer();
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_card_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.promptText.setText(prompts[position]);
        holder.answerText.setText(answers[position]);
    }


    @Override
    public int getItemCount() {
        return prompts.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView promptText;
        TextView answerText;
        Button revealButton;

        public ViewHolder(View itemView) {
            super(itemView);
            promptText = itemView.findViewById(R.id.idCardPrompt);
            answerText = itemView.findViewById(R.id.idCardAnswer);
            revealButton = itemView.findViewById(R.id.idRevealButton);
        }
    }
}
