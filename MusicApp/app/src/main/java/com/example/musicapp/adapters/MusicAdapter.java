package com.example.musicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.MediaPlayerScreen;
import com.example.musicapp.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import soup.neumorphism.NeumorphCardView;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    ArrayList<String> musicFiles;
    Context context;

    public MusicAdapter(ArrayList<String> musicFiles, Context context) {
        this.musicFiles = musicFiles;
        this.context = context;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        File file = new File(musicFiles.get(position));
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            holder.textView.setText(fileName.substring(0, dotIndex));
        }

        holder.neumorphCardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MediaPlayerScreen.class);
            intent.putExtra("title", fileName.substring(0, dotIndex));
            intent.putExtra("filePath", musicFiles.get(position));
            intent.putExtra("position", position);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST",(Serializable)musicFiles);
            intent.putExtra("music_list",args);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return musicFiles.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        NeumorphCardView neumorphCardView;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.songTitle);
            neumorphCardView = itemView.findViewById(R.id.cardView);
        }
    }
}
