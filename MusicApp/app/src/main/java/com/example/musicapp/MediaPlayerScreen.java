package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayerScreen extends AppCompatActivity {
    ImageView imageViewPrevious, imageViewNext, imageViewPlay;
    String title, filePath;
    int position;
    TextView textView, textViewElapseTime, textViewTotalTime;
    SeekBar seekBar;
    ArrayList<String> musicFiles = new ArrayList<>();
    MediaPlayer mediaPlayer;
    Runnable runnable;
    Handler handler;
    int totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        imageViewNext = findViewById(R.id.next);
        imageViewPrevious = findViewById(R.id.previous);
        imageViewPlay = findViewById(R.id.pause);
        textView = findViewById(R.id.titleMusic);
        seekBar = findViewById(R.id.sliderFile);
        textViewElapseTime = findViewById(R.id.elapseTime);
        textViewTotalTime = findViewById(R.id.totalTime);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        filePath = intent.getStringExtra("filePath");
        position = intent.getIntExtra("position", 0);
        Bundle args = intent.getBundleExtra("music_list");
        musicFiles = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        textView.setText(title);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /// Playing and pausing the music file
        imageViewPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                imageViewPlay.setImageResource(R.drawable.pause);
            } else {
                mediaPlayer.start();
                imageViewPlay.setImageResource(R.drawable.play);
            }
        });


        imageViewNext.setOnClickListener(v -> {
            mediaPlayer.reset();
            if (position == musicFiles.size() - 1) {
                position = 0;
            } else {
                position = position + 1;
            }

            try {
                mediaPlayer.setDataSource(musicFiles.get(position));
                mediaPlayer.prepare();
                mediaPlayer.start();
                imageViewPlay.setImageResource(R.drawable.play);
                File file = new File(musicFiles.get(position));
                String fileName = file.getName();
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex != -1) {
                    textView.setText(fileName.substring(0, dotIndex));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        imageViewPrevious.setOnClickListener(v -> {
            mediaPlayer.reset();
            if (position == 0) {
                position = musicFiles.size() - 1;
            } else {
                position = position - 1;
            }
            try {
                mediaPlayer.setDataSource(musicFiles.get(position));
                mediaPlayer.prepare();
                mediaPlayer.start();
                imageViewPlay.setImageResource(R.drawable.play);

                File file = new File(musicFiles.get(position));
                String fileName = file.getName();
                int dotIndex = fileName.lastIndexOf('.');
                if (dotIndex != -1) {
                    textView.setText(fileName.substring(0, dotIndex));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        handler = new Handler();
        runnable = () -> {
            totalTime = mediaPlayer.getDuration();
            seekBar.setMax(totalTime);
            int currentPosition = mediaPlayer.getCurrentPosition();
            seekBar.setProgress(currentPosition);

            handler.postDelayed(runnable, 1000);
            String elapsedTime = createTimeLabel(currentPosition);
            String lastTime = createTimeLabel(totalTime);
            textViewElapseTime.setText(elapsedTime);
            textViewTotalTime.setText(lastTime);

            if (elapsedTime.equals(lastTime)) {
                mediaPlayer.reset();
                if (position == musicFiles.size() - 1) {
                    position = 0;
                } else {
                    position = position + 1;
                }

                try {
                    mediaPlayer.setDataSource(musicFiles.get(position));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    imageViewPlay.setImageResource(R.drawable.play);
                    File file = new File(musicFiles.get(position));
                    String fileName = file.getName();
                    int dotIndex = fileName.lastIndexOf('.');
                    if (dotIndex != -1) {
                        textView.setText(fileName.substring(0, dotIndex));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        };
        handler.post(runnable);
    }


    public String createTimeLabel(int currentPosition) {
        String timeLabel;
        int minute, second;
        minute = currentPosition / 1000 / 60;
        second = currentPosition / 1000 % 60;
        if (second < 10) {
            timeLabel = minute + ":0" + second;
        } else {
            timeLabel = minute + ":" + second;
        }
        return timeLabel;
    }
}