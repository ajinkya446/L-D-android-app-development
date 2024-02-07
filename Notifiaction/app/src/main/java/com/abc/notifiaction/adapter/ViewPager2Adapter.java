package com.abc.notifiaction.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.notifiaction.R;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {

    // Array of images
    // Adding images from drawable folder
    private int[] images = {R.drawable.exam4, R.drawable.exam2, R.drawable.exam3};
    private String[] titleArray =new String[] {"UNLOCKING BENEFITS", "TAKING BENEFITED\n PUZZLES", "LIMITED TIME\nQUESTIONS"};
    private String[] descArray = new String[] {"Continuous effort is the key \nto unlocking our potential", "affordances for gameful experiences in \norder to support user's \noverall value creation", "You give but little when \nyou give of your possessions"};
    private Context ctx;

    // Constructor of our ViewPager2Adapter class
    public ViewPager2Adapter(Context ctx) {
        this.ctx = ctx;
    }

    // This method returns our layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
        return new ViewHolder(view);
    }

    // This method binds the screen with the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // This will set the images in imageview
        holder.images.setImageResource(images[position]);
        holder.textViewTitle.setText(titleArray[position]);
        holder.textViewDesc.setText(descArray[position]);
    }

    // This Method returns the size of the Array
    @Override
    public int getItemCount() {
        return images.length;
    }

    // The ViewHolder class holds the view
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView textViewTitle, textViewDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            images = itemView.findViewById(R.id.images);
            textViewTitle = itemView.findViewById(R.id.titles);
            textViewDesc = itemView.findViewById(R.id.description);
        }
    }
}
