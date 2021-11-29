package com.example.acnh_wiki.song;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.acnh_wiki.R;
import com.example.acnh_wiki.Utils;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class SongActivity extends AppCompatActivity {
    TextView name, sellPrice, buyPrice, isOrderable;
    ImageView imageView;
    SeekBar seekBar;

    TextView songDuration;
    MaterialButton playBtn;
    MediaPlayer mediaPlayer;
    Runnable runnable;
    Handler handler;

    SongEntity song;
    boolean isPrepared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        init();

        Picasso.with(this).load(song.getImageUri()).into(imageView);

        name.setText(Utils.capitalizeString(song.getName().getNameEUen()));
        String text = song.getSellPrice() + " \uD83D\uDCB0";
        sellPrice.setText(text);

        text = song.getByePrice() != null ? song.getByePrice() + " \uD83D\uDCB0" : "NaN";
        buyPrice.setText(text);

        text = song.getIsOrderable().equals("true") ?
                "You can order this song" :
                "You can not order this song";

        isOrderable.setText(text);

        playBtn.setOnClickListener(playClickListener);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playBtn.setBackgroundResource(R.drawable.btn_play);
            }
        });
    }

    private void init() {
        Intent intent = getIntent();
        song = (SongEntity) intent.getExtras().getSerializable("song");

        imageView = findViewById(R.id.song_detail_image);
        name = findViewById(R.id.song_detail_name);
        sellPrice = findViewById(R.id.song_detail_sell_price);
        buyPrice = findViewById(R.id.song_detail_buy_price);
        isOrderable = findViewById(R.id.song_orderable);

        /* Media player */
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();

        seekBar = findViewById(R.id.song_seek_bar);
        playBtn = findViewById(R.id.button_play);
        songDuration = findViewById(R.id.song_duration);

        /* For SeekBar */
        handler = new Handler();
    }

    private View.OnClickListener playClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mediaPlayer.isPlaying()) {
                playBtn.setBackgroundResource(R.drawable.btn_play);
                mediaPlayer.pause();
            } else {
                playBtn.setBackgroundResource(R.drawable.btn_pause);
                if(isPrepared) {
                    mediaPlayer.start();
                    updateSeekBar();
                } else {
                    playAudio();
                }
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) {
                mediaPlayer.seekTo(progress);
                seekBar.setProgress(progress);
            }
            String text = Utils.getTimeString(progress) + " / " + Utils.getTimeString(seekBar.getMax());
            songDuration.setText(text);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void playAudio() {
        try {
            Uri uri = Uri.parse(song.getMusicUri());
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    isPrepared = true;
                    seekBar.setMax(mp.getDuration());
                    mediaPlayer.start();
                    updateSeekBar();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                double ration = percent / 100.0;
                int bufferingLevel = (int)(mp.getDuration() * ration);
                seekBar.setSecondaryProgress(bufferingLevel);
            }
        });
    }

    private void updateSeekBar() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        seekBar.setProgress(currentPosition);

        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}