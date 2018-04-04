package com.example.eslamfaisal.musicalstructure;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AllSongsActivity extends AppCompatActivity {
    private ImageView b1;
    private ImageView b2;
    private ImageView b3;
    private ImageView b4;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();

    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1, tx2;
    public static int oneTimeOnly = 0;
    private int start = 0;
    ImageView playImage;
    boolean isPlay = false;
    TextView songNamePlay;
    MediaPlayer mediaPlayer;
    int position;

    boolean hh = false;
    int a = 1;

    int temp = 0;

    private boolean mPetHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mPetHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);

        b1 = (ImageView) findViewById(R.id.button1);
        b2 = (ImageView) findViewById(R.id.button2);
        b3 = (ImageView) findViewById(R.id.button3);
        b4 = (ImageView) findViewById(R.id.button4);

        tx1 = (TextView) findViewById(R.id.textView2);
        tx2 = (TextView) findViewById(R.id.textView3);

        seekbar = (SeekBar) findViewById(R.id.seekBar);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (mPetHasChanged) {
                    mediaPlayer.seekTo(i);
                    mPetHasChanged = false;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        songNamePlay = (TextView) findViewById(R.id.song_name_playing_in_all_songs);
        setTitle("All Songs");
        final ArrayList<SongDetailes> songs = SongsListe.getAll();
        ListView listView = (ListView) findViewById(R.id.list_of_all_songs);
        AllSongsAdapter adapter = new AllSongsAdapter(this, songs);
        listView.setAdapter(adapter);
        songNamePlay.setText("No Playing");
        playImage = (ImageView) findViewById(R.id.playing_button_in_all_songs);
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (start == 1) {
                    if (!isPlay) {
                        mediaPlayer.start();
                        playImage.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                        SongDetailes currentSong = songs.get(position);
                        String songName = currentSong.getName();
                        songNamePlay.setText(songName);
                        isPlay = true;
                    } else {
                        playImage.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        isPlay = false;
                        songNamePlay.setText("Is Paused");
                        mediaPlayer.pause();

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Choos a song", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seekbar.setOnTouchListener(mTouchListener);

                start = 1;
                releaseMediaPlayer();

                playImage.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);

                isPlay = true;

                position = i;
                SongDetailes currentSong = songs.get(i);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), currentSong.getSongId());
                duration();
                mediaPlayer.start();

                String nameOfCurrentSong = "( " + currentSong.getName() + " )";
                songNamePlay.setText(nameOfCurrentSong);


                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer media1) {
                        position++;
                        SongDetailes currentSong = songs.get(position);
                        mediaPlayer = MediaPlayer.create(getApplicationContext(), currentSong.getSongId());
                        duration();
                        String songName = currentSong.getName();
                        songNamePlay.setText(songName);
                        mediaPlayer.start();
                    }
                });
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay != false) {
                    releaseMediaPlayer();
                    if (position > 0) {
                        position--;
                    }
                    SongDetailes currentSong = songs.get(position);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), currentSong.getSongId());
                    duration();
                    String songName = currentSong.getName();
                    songNamePlay.setText(songName);
                    mediaPlayer.start();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay != false) {
                    releaseMediaPlayer();
                    if (position < songs.size() - 1)
                        position++;
                    SongDetailes currentSong = songs.get(position);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), currentSong.getSongId());
                    duration();
                    String songName = currentSong.getName();
                    songNamePlay.setText(songName);
                    mediaPlayer.start();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start == 1) {
                    int temp = (int) startTime;
                    if ((temp + forwardTime) <= finalTime) {
                        startTime = startTime + forwardTime;
                        mediaPlayer.seekTo((int) startTime);
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot jump forward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start == 1) {
                    int temp = (int) startTime;

                    if ((temp - backwardTime) > 0) {
                        startTime = startTime - backwardTime;
                        mediaPlayer.seekTo((int) startTime);
                    } else {
                        Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d:%d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 1000);
        }
    };

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    private void duration() {
        finalTime = mediaPlayer.getDuration();
        startTime = mediaPlayer.getCurrentPosition();

        seekbar.setMax((int) finalTime);

        tx2.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                finalTime)))
        );

        tx1.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                startTime)))
        );

        // seekbar.setProgress((int) startTime);
        myHandler.postDelayed(UpdateSongTime, 1000);

    }

    @Override
    protected void onDestroy() {
        releaseMediaPlayer();
        super.onDestroy();
        releaseMediaPlayer();
    }
}
