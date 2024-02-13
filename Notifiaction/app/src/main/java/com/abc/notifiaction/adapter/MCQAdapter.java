package com.abc.notifiaction.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.notifiaction.R;
import com.abc.notifiaction.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MCQAdapter extends RecyclerView.Adapter<MCQAdapter.MCQViewHolder> {
    ArrayList<String> answersList;
    public Context context;
    String answer;
    int selectedPage = -1;
    private RecyclerViewInterface recyclerViewInterface;

    public MCQAdapter(ArrayList<String> answersList, Context context, String answer, RecyclerViewInterface recyclerViewInterface) {
        this.answersList = answersList;
        this.context = context;
        this.answer = answer;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MCQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mcq, parent, false);
        return new MCQAdapter.MCQViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MCQViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.radioButton.setText(answersList.get(position));

        holder.radioButton.setOnClickListener(v -> {
            if (selectedPage == -1) {
                selectedPage = position;
                notifyDataSetChanged();
                recyclerViewInterface.recyclerViewListClicked(holder.itemView, position);
                if (answer == answersList.get(position)) {
                    holder.cardView.setBackgroundColor(Color.parseColor("#05C60C"));
                    holder.radioButton.setTextColor(ContextCompat.getColor(context, R.color.white));
                } else {
                    holder.cardView.setBackgroundColor(Color.parseColor("#EEFB0000"));
                    holder.radioButton.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
            } else {
                holder.radioButton.setChecked(false);
            }
        });
        if (selectedPage == position) {
            holder.radioButton.setChecked(true);
        } else {
            holder.radioButton.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }

    public class MCQViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        CardView cardView;

        public MCQViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radioButton);
            cardView = itemView.findViewById(R.id.cardViewAnswer);

        }

    }
}
